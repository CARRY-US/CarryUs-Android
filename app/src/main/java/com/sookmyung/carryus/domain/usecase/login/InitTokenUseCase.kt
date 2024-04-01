package com.sookmyung.carryus.domain.usecase.login

import com.sookmyung.carryus.domain.repository.AuthRepository
import javax.inject.Inject

class InitTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(accessToken: String, refreshToken: String) =
        authRepository.initToken(accessToken, refreshToken)
}
