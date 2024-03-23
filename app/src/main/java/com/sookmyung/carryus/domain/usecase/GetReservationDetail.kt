package com.sookmyung.carryus.domain.usecase

import com.sookmyung.carryus.domain.repository.ReservationsRepository
import javax.inject.Inject

class GetReservationDetail @Inject constructor(
    private val reservationsRepository: ReservationsRepository
){
    suspend operator fun invoke(reservationId: Int) =
        reservationsRepository.getReservationDetail(reservationId)
}
