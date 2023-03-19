package com.example.kotlinApp.ui

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.lotlinApp.R

class SplashScreen : AppCompatActivity() {

    lateinit var splashImg : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

            findViews()
    }

    private fun findViews() {
        splashImg = findViewById(R.id.splash_img)
       val animation = AnimationUtils.loadAnimation(this,R.anim.animation)
        splashImg.startAnimation(animation)

        Handler().postDelayed(
            {
             val intent = Intent(this,MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            },3000

        )

    }
}