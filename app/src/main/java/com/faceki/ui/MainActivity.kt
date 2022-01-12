package com.faceki.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.faceki.R
import com.faceki.network.ApiCall
import com.faceki.network.IApiCallback
import com.faceki.response.GetTokenResponse
import com.faceki.utils.MyApplication
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Objects.requireNonNull(supportActionBar)!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        setContentView(R.layout.activity_main)

//        setAppLanguage()
//        apiCall()
    }

    /*private fun setAppLanguage() {
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
    }*/

    /*private fun apiCall() {
        val hashMap = HashMap<String, String>()
        hashMap["client_id"] = getString(R.string.client_id)
        hashMap["email"] = getString(R.string.email)

        ApiCall.instance?.getToken(hashMap, this)
    }

    override fun onSuccess(type: String, data: Any?) {
        MyApplication.spinnerStop()
        val responseGet: Response<Any> = data as Response<Any>
        if (responseGet.isSuccessful) {
            val objectType = object : TypeToken<GetTokenResponse>() {}.type
            val getTokenResponse: GetTokenResponse = Gson().fromJson(Gson().toJson(responseGet.body()), objectType)
            MyApplication.setSharedPrefString("token", "Bearer " + getTokenResponse.token)
//            startActivity(Intent(this, FaceDetection::class.java))
            startActivity(Intent(this, Confirmation::class.java))
            finish()
        } else
            MyApplication.showMassage(this, getString(R.string.error))
    }

    override fun onFailure(data: Any?) {
        MyApplication.spinnerStop()
        MyApplication.showMassage(this, data.toString())
    }*/

}