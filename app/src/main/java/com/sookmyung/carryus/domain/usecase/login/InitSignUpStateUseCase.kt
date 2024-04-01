package com.sookmyung.carryus.domain.usecase.login

import com.sookmyung.carryus.domain.repository.AuthRepository
import javax.inject.Inject

class InitSignUpStateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(isSignUpState: Boolean) =
        authRepository.initSignUpState(isSignUpState)
}
