package com.sookmyung.carryus.data.service

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.request.ReviewRequest
import com.sookmyung.carryus.data.entitiy.response.ReviewDetailResponse
import com.sookmyung.carryus.data.entitiy.response.ReviewResponse
import com.sookmyung.carryus.data.entitiy.response.ReviewStoreInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ReviewsService {
    @GET("reviews/{reviewId}")
    suspend fun getReviewDetail(
        @Path("reviewId") reviewId: Int
    ) : BaseResponse<ReviewDetailResponse>

    @GET("reviews/{reviewId}/store/info")
    suspend fun getReviewStoreInfo(
        @Path("reviewId") reviewId: Int
    ) : BaseResponse<ReviewStoreInfoResponse>

    @PATCH("reviews/{reviewId}")
    suspend fun updateReview(
        @Path("reviewId") reviewId: Int,
        @Body reviewRequest: ReviewRequest
    ) : BaseResponse<ReviewResponse>
}
