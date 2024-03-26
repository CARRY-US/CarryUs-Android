package com.sookmyung.carryus.domain.repository

import com.sookmyung.carryus.data.entitiy.request.ReviewRequest
import com.sookmyung.carryus.data.entitiy.response.ReviewResponse
import com.sookmyung.carryus.domain.entity.ReviewDetail
import com.sookmyung.carryus.domain.entity.ReviewStoreInfo

interface ReviewsRepository {
    suspend fun getReviewDetail(
        reviewId: Int
    ): Result<ReviewDetail>

    suspend fun getReviewStoreInfo(
        reviewId: Int
    ): Result<ReviewStoreInfo>

    suspend fun updateReview(
        reviewId: Int, reviewRequest: ReviewRequest
    ): Result<ReviewResponse>
}
