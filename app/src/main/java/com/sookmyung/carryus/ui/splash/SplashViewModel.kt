package com.sookmyung.carryus.ui.splash

import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.usecase.login.GetSignedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSignedInUseCase: GetSignedInUseCase
) : ViewModel() {
    fun isSignedIn(): Boolean = getSignedInUseCase()
}
