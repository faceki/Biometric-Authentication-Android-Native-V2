package com.faceki.ui

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.faceki.R
import com.faceki.databinding.ActivitySuccessfulBinding
import com.faceki.model.SuccessPageModel
import com.faceki.utils.StatusCodes.Companion.getStatusCodeFromInt
import java.util.*

class Successful : AppCompatActivity() {
    lateinit var binding: ActivitySuccessfulBinding
    lateinit var successPageModel: SuccessPageModel
    private var activityStatus = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Objects.requireNonNull(supportActionBar)?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_successful)

        successPageModel = intent.getSerializableExtra("SuccessPageModel") as SuccessPageModel
        if(successPageModel?.data?.logedIn==true){
            Handler(Looper.getMainLooper()).postDelayed({
                if (activityStatus)
                    openLink()
            }, 5000)
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        var icon : Int = 0
        var title = ""
        if(successPageModel.type == "Login") {
            if(successPageModel.data?.logedIn==true){
                icon = R.drawable.success_gif
                title = getString(R.string.successful)
                binding.name.visibility = View.VISIBLE
            } else{
                icon = R.drawable.fail_gif
                title = "${getString(R.string.fail)} - ${successPageModel.data?.message}"
                binding.name.visibility = View.INVISIBLE
            }
        } else if(successPageModel.type == "SignUp"){
            if(successPageModel.responseCode==0){
                icon = R.drawable.success_gif
                title = getString(R.string.successful)
                binding.name.visibility = View.VISIBLE
            } else{
                icon = R.drawable.fail_gif
                title = "${getString(R.string.fail)} - ${successPageModel.data?.message}"
                binding.name.visibility = View.INVISIBLE
            }
        }


        binding.image.setImageResource(icon)
        binding.title.text = title
        successPageModel.data?.user?.email?.let {
            if (it != "")
                binding.name.text = getString(R.string.welcome) + "\n" + it
            else
                binding.name.text = ""
        }
        successPageModel.data?.user?.selfieImage.let {
            binding.link.text = it ?: ""
        }

        binding.link.paint.isUnderlineText = true

        binding.back.setOnClickListener {
            startActivity(Intent(this, Confirmation::class.java))
            finish()
        }
    }

    override fun onPause() {
        super.onPause()

        activityStatus = false
    }

    override fun onRestart() {
        super.onRestart()

        startActivity(Intent(this, FaceDetection::class.java))
        finish()
    }

    private fun openLink() {
        var link = successPageModel.data?.user?.selfieImage
        if (link?.isNotEmpty()==true) {
            if (!link.startsWith("https://"))
                link = "https://$link"
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                ContextCompat.startActivity(this, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
                Toast.makeText(this, getString(R.string.wrong_url), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
