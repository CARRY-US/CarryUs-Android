package com.sookmyung.carryus.domain.usecase.reservation

import com.sookmyung.carryus.domain.repository.ReservationsRepository
import javax.inject.Inject

class GetReservationListUseCase @Inject constructor(
    private val reservationsRepository: ReservationsRepository
){
    suspend operator fun invoke(status: String) =
        reservationsRepository.getReservationList(status)
}
