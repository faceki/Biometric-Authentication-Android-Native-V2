package com.faceki.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.faceki.R
import com.faceki.databinding.ActivitySignUpBinding
import com.faceki.model.SignUpDetailModel
import java.util.*

class SignUp : AppCompatActivity(){ //, IApiCallback {
    lateinit var binding: ActivitySignUpBinding
    private var path: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Objects.requireNonNull(supportActionBar)!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        path = intent.getStringExtra("imagePath").toString()
    }

    override fun onStart() {
        super.onStart()

        binding.next.setOnClickListener {
            checkValidation()
        }
    }

    private fun checkValidation() {
        val firstName = binding.firstName.text.toString().trim()
        val lastName = binding.lastName.text.toString().trim()
        val number = binding.number.text.toString().trim()
        val email = binding.email.text.toString().trim()
        val code: String = binding.ccp.selectedCountryCode
//        val country: String = binding.ccp.selectedCountryEnglishName

        if (firstName.isNotEmpty())
            if (lastName.isNotEmpty())
                if (number.isNotEmpty())
                    if (email.isNotEmpty()) {
                        val signUpDetailModel = SignUpDetailModel()
                        signUpDetailModel.name = "$firstName $lastName"
                        signUpDetailModel.number = code + number
                        signUpDetailModel.email = email

                        startActivity(
                            Intent(this, FaceDetection::class.java)
                                .putExtra("type", "signup")
                                .putExtra("SignUpDetailModel", signUpDetailModel)
                        )

                        /*val hashMap = HashMap<String, Any>()
                        hashMap["client_id"] = getString(R.string.client_id)
                        hashMap["name"] = "$firstName $lastName"
                        hashMap["mobile_number"] = code + number
                        hashMap["email"] = email

                        MyApplication.spinnerStart(this)
                        ApiCall.instance?.signup(
                            MyApplication.getSharedPrefString("token"),
                            RetrofitUtils.createFilePart(
                                "image",
                                path,
                                RetrofitUtils.MEDIA_TYPE_IMAGE_ALL
                            ),
                            RetrofitUtils.createMultipartRequest(hashMap),
                            this
                        )*/
                    } else binding.email.error = getString(R.string.please_enter_email_address)
                else binding.number.error = getString(R.string.please_enter_mobile_number)
            else binding.lastName.error = getString(R.string.please_enter_last_name)
        else binding.firstName.error = getString(R.string.please_enter_first_name)
    }

   /* override fun onSuccess(type: String, data: Any?) {
        MyApplication.spinnerStop()
        val responseGet: Response<Any> = data as Response<Any>
        if (responseGet.isSuccessful) {
            val successPageModel = SuccessPageModel()
            successPageModel.image = R.drawable.success_gif
            successPageModel.title = "Successful"

            startActivity(
                Intent(this, Successful::class.java)
                    .putExtra("SuccessPageModel", successPageModel)
            )
            finish()
        } else {
            MyApplication.showMassage(this, getString(R.string.error))
        }
    }

    override fun onFailure(data: Any?) {
        MyApplication.spinnerStop()
        MyApplication.showMassage(this, data.toString())
    }*/
}