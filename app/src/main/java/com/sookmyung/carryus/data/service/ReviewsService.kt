package com.sookmyung.carryus.data.service

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.response.ReviewDetailResponse
import com.sookmyung.carryus.data.entitiy.response.ReviewStoreInfoResponse
import retrofit2.http.GET
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
}
