package com.sookmyung.carryus.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.data.service.KakaoLoginService
import com.sookmyung.carryus.databinding.ActivityLoginBinding
import com.sookmyung.carryus.ui.main.MainActivity
import com.sookmyung.carryus.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    @Inject
    lateinit var kakaoLoginService: KakaoLoginService

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initKakaoLoginBtnClickListener()
        observeKakaoLogin()
        observeSignUp()
    }

    private fun initKakaoLoginBtnClickListener() {
        binding.btnLoginKakao.setOnClickListener {
            startKakaoLogin()
        }
    }

    private fun startKakaoLogin() {
        kakaoLoginService.startKakaoLogin(viewModel.kakaoLoginCallback)
    }

    private fun observeKakaoLogin() {
        viewModel.isKakaoLogin.observe(this) { isKakaoLogin ->
            if (isKakaoLogin) {
                viewModel.postLogin()
            } else {
                Timber.e("카카오 로그인 실패")
            }
        }
    }

    private fun observeSignUp() {
        viewModel.isSignedIn.observe(this) { isSignedUp ->
            if (isSignedUp) {
                startActivity(
                    Intent(this, MainActivity::class.java)
                )
                finish()
            }
        }
    }
}
