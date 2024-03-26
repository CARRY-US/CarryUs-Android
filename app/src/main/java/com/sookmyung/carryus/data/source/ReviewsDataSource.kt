package com.sookmyung.carryus.data.source

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.response.ReviewDetailResponse
import com.sookmyung.carryus.data.entitiy.response.ReviewStoreInfoResponse
import com.sookmyung.carryus.data.service.ReviewsService
import javax.inject.Inject

class ReviewsDataSource @Inject constructor(
    private val reviewsService: ReviewsService
) {
    suspend fun getReviewDetail(reviewId: Int) : BaseResponse<ReviewDetailResponse> =
        reviewsService.getReviewDetail(reviewId)

    suspend fun getReviewStoreInfo(reviewId: Int) : BaseResponse<ReviewStoreInfoResponse> =
        reviewsService.getReviewStoreInfo(reviewId)
}
