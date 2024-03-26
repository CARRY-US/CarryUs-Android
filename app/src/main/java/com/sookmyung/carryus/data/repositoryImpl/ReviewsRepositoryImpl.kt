package com.sookmyung.carryus.data.repositoryImpl

import com.sookmyung.carryus.data.entitiy.request.ReviewRequest
import com.sookmyung.carryus.data.entitiy.response.ReviewResponse
import com.sookmyung.carryus.data.source.ReviewsDataSource
import com.sookmyung.carryus.domain.entity.ReviewDetail
import com.sookmyung.carryus.domain.entity.ReviewStoreInfo
import com.sookmyung.carryus.domain.repository.ReviewsRepository
import javax.inject.Inject

class ReviewsRepositoryImpl @Inject constructor(
    private val reviewsDataSource: ReviewsDataSource
): ReviewsRepository {
    override suspend fun getReviewDetail(reviewId: Int) : Result<ReviewDetail>
    = runCatching {
        reviewsDataSource.getReviewDetail(reviewId)
    }.mapCatching { response ->
        response.data?.toReviewDetail() ?: ReviewDetail()
    }

    override suspend fun getReviewStoreInfo(reviewId: Int) : Result<ReviewStoreInfo>
    = runCatching {
        reviewsDataSource.getReviewStoreInfo(reviewId)
    }.mapCatching { response ->
        response.data?.toReviewStoreInfo() ?: ReviewStoreInfo()
    }

    override suspend fun updateReview(reviewId: Int, reviewRequest: ReviewRequest) : Result<ReviewResponse>
    = runCatching {
        reviewsDataSource.updateReview(reviewId, reviewRequest)
    }.mapCatching { response ->
        response.data?.toReviewResponse() ?: ReviewResponse()
    }
}
