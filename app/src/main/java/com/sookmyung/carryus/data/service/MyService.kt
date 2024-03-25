package com.sookmyung.carryus.data.service

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.response.MyProfileResponse
import com.sookmyung.carryus.data.entitiy.response.MyReviewsResponse
import retrofit2.http.GET

interface MyService {
    @GET("my/profile")
    suspend fun getMyProfile() : BaseResponse<MyProfileResponse>

    @GET("my/reviews")
    suspend fun getMyReviews() : BaseResponse<List<MyReviewsResponse>>
}
