package com.sookmyung.carryus.domain.usecase

import com.sookmyung.carryus.domain.repository.ReservationsRepository
import javax.inject.Inject

class GetReservationList @Inject constructor(
    private val reservationsRepository: ReservationsRepository
){
    suspend operator fun invoke(status: String) =
        reservationsRepository.getReservationList(status)
}
