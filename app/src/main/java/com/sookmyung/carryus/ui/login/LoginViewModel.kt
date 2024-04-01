package com.sookmyung.carryus.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.sookmyung.carryus.domain.usecase.login.InitTokenUseCase
import com.sookmyung.carryus.domain.usecase.login.PostLoginUseCase
import com.sookmyung.carryus.util.KakaoLoginCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase,
    private val initTokenUseCase: InitTokenUseCase
) : ViewModel() {
    private val _isKakaoLogin = MutableLiveData(false)
    val isKakaoLogin get() = _isKakaoLogin

    private val _isSignedUp = MutableLiveData(false)
    val isSignedUp get() = _isSignedUp

    val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        KakaoLoginCallback { accessToken ->
            _isKakaoLogin.value = true
            initTokenUseCase(accessToken = accessToken, refreshToken = "")
        }.handleResult(token, error)
    }

    fun postLogin() {
        viewModelScope.launch {
            postLoginUseCase(PLATFORM_TYPE, ROLE)
                .onSuccess { response ->
                    initTokenUseCase(response.accessToken, response.refreshToken)
                    _isSignedUp.value = true
                    Timber.e("accessToken: ${response.accessToken}, refreshToken: ${response.refreshToken}")
                }.onFailure { throwable ->
                    Timber.e("$throwable")
                }
        }
    }

    companion object {
        private const val PLATFORM_TYPE = "KAKAO"
        private const val ROLE = "TRAVELER"
    }
}
