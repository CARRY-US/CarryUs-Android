package com.sookmyung.carryus.domain.usecase.reservation

import com.sookmyung.carryus.data.entitiy.request.CancelReservationRequest
import com.sookmyung.carryus.domain.repository.ReservationsRepository
import javax.inject.Inject

class postCancelReservationUseCase @Inject constructor(
    private val reservationsRepository: ReservationsRepository
){
    suspend operator fun invoke(cancelReservationRequest: CancelReservationRequest) =
        reservationsRepository.postCancelReservation(cancelReservationRequest)
}
