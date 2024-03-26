package com.sookmyung.carryus.domain.usecase

import com.sookmyung.carryus.domain.repository.ReservationRepository
import javax.inject.Inject

class GetUserDefaultInfoUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository
) {
    suspend operator fun invoke() =
        reservationRepository.getUserDefaultInfo()
}
