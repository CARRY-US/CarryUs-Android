package com.sookmyung.carryus.data.source

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.response.MyProfileResponse
import com.sookmyung.carryus.data.entitiy.response.MyReviewsResponse
import com.sookmyung.carryus.data.service.MyService
import javax.inject.Inject

class MyDataSource @Inject constructor(
    private val myService: MyService
) {
    suspend fun getMyProfile() :BaseResponse<MyProfileResponse> =
        myService.getMyProfile()

    suspend fun getMyReviews() :BaseResponse<List<MyReviewsResponse>> =
        myService.getMyReviews()
}
