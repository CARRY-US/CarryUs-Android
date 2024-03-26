package com.sookmyung.carryus.domain.usecase.review

import com.sookmyung.carryus.domain.repository.ReviewsRepository
import javax.inject.Inject

class GetReviewStoreInfoUseCase @Inject constructor(
  private val reviewsRepository: ReviewsRepository
){
    suspend operator fun invoke(reviewId: Int) =
        reviewsRepository.getReviewStoreInfo(reviewId)
}
