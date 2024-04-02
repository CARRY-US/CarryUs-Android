package com.sookmyung.carryus.domain.usecase.login

import com.sookmyung.carryus.domain.repository.AuthRepository
import javax.inject.Inject

class GetSignedInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.getSignedIn()
}
