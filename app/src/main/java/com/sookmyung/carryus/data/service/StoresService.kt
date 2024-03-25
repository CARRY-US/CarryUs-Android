package com.sookmyung.carryus.data.service

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.response.LocationStoreResponse
import com.sookmyung.carryus.data.entitiy.response.StoreDetailInfoResponse
import com.sookmyung.carryus.data.entitiy.response.StoreDetailReviewResponse
import com.sookmyung.carryus.data.entitiy.response.UserLocationStoreResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoresService {
    @GET("/stores/{storeId}/detail")
    suspend fun getStoreDetailInfo(
        @Path("storeId") storeId: Int
    ): BaseResponse<StoreDetailInfoResponse>

    @GET("/stores/{storeId}/reviews")
    suspend fun getStoreDetailReview(
        @Path("storeId") storeId: Int
    ): BaseResponse<StoreDetailReviewResponse>
}
