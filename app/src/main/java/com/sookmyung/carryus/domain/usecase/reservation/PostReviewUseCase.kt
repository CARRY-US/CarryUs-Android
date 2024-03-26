package com.sookmyung.carryus.domain.usecase.reservation

import com.sookmyung.carryus.data.entitiy.request.ReviewRequest
import com.sookmyung.carryus.domain.repository.ReservationsRepository
import javax.inject.Inject

class PostReviewUseCase @Inject constructor(
    private val reservationsRepository: ReservationsRepository
){
    suspend operator fun invoke(reservationId: Int, reviewRequest: ReviewRequest) =
        reservationsRepository.postReview(reservationId, reviewRequest)
}
