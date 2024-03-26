package com.sookmyung.carryus.domain.usecase.review

import com.sookmyung.carryus.data.entitiy.request.ReviewRequest
import com.sookmyung.carryus.domain.repository.ReviewsRepository
import javax.inject.Inject

class PatchReviewUseCase @Inject constructor(
    private val reviewsRepository: ReviewsRepository
){
    suspend operator fun invoke(reviewId: Int, reviewRequest: ReviewRequest) =
        reviewsRepository.updateReview(reviewId, reviewRequest)
}
