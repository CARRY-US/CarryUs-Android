package com.sookmyung.carryus.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivitySplashBinding
import com.sookmyung.carryus.ui.main.MainActivity
import com.sookmyung.carryus.util.binding.BindingActivity

class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSplash()
    }

    private fun initSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY)
    }

    companion object {
        const val SPLASH_DELAY = 2000L
    }
}
