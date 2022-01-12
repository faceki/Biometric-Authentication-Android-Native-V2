package com.faceki.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.faceki.R
import com.faceki.databinding.ActivityConfirmationBinding
import com.faceki.network.ApiCall
import com.faceki.network.IApiCallback
import com.faceki.response.GetTokenResponse
import com.faceki.utils.MyApplication
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.util.*

class Confirmation : AppCompatActivity(), View.OnClickListener, IApiCallback {
    lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Objects.requireNonNull(supportActionBar)!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_confirmation)

        setAppLanguage()
        apiCall()
    }

    private fun setAppLanguage() {
        when (Locale.getDefault().displayLanguage) {
            "العربية" -> changeLanguage("ar")
            "English" -> changeLanguage("en")
        }
    }

    private fun changeLanguage(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale
        this.resources.updateConfiguration(configuration, this.resources.displayMetrics)
    }

    override fun onStart() {
        super.onStart()

        binding.signIn.setOnClickListener(this)
        binding.signUp.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.signIn -> startActivity(Intent(this, FaceDetection::class.java).putExtra("type", "login"))
            binding.signUp -> startActivity(Intent(this, SignUp::class.java))
        }
    }

    private fun apiCall() {
        val hashMap = HashMap<String, String>()
        hashMap["client_id"] = getString(R.string.client_id)
        hashMap["email"] = getString(R.string.email)

        MyApplication.spinnerStart(this)
        ApiCall.instance?.getToken(hashMap, this)
    }

    override fun onSuccess(type: String, data: Any?) {
        MyApplication.spinnerStop()
        val responseGet: Response<Any> = data as Response<Any>
        if (responseGet.isSuccessful) {
            val objectType = object : TypeToken<GetTokenResponse>() {}.type
            val getTokenResponse: GetTokenResponse = Gson().fromJson(Gson().toJson(responseGet.body()), objectType)
            MyApplication.setSharedPrefString("token", "Bearer " + getTokenResponse.token)

        } else
            MyApplication.showMassage(this, getString(R.string.error))
    }

    override fun onFailure(data: Any?) {
        MyApplication.spinnerStop()
        MyApplication.showMassage(this, data.toString())
    }
}