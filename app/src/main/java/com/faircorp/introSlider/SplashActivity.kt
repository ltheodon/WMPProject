package com.faircorp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setDecorFitsSystemWindows(false)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(object: Runnable {
            override fun run() {
                startActivity(Intent(this@SplashActivity, IntroSliderActivity::class.java))
                finish()
            }
        }, 2000)
    }
}
