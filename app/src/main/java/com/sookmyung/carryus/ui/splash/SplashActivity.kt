package com.sookmyung.carryus.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivitySplashBinding
import com.sookmyung.carryus.ui.login.LoginActivity
import com.sookmyung.carryus.ui.main.MainActivity
import com.sookmyung.carryus.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BindingActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSplash()
    }

    private fun initSplash() {
        lateinit var intent: Intent
        Handler(Looper.getMainLooper()).postDelayed({
            intent = if (viewModel.isSignedUp()) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, LoginActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, SPLASH_DELAY)
    }

    companion object {
        const val SPLASH_DELAY = 2000L
    }
}
