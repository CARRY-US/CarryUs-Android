package com.sookmyung.carryus.domain.usecase.login

import com.sookmyung.carryus.domain.repository.AuthRepository
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(token: String, socialPlatform: String, role: String) = authRepository.postLogin(token, socialPlatform, role)
}
