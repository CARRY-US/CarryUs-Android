package com.sookmyung.carryus.data.service

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.request.StoreReservationRequest
import com.sookmyung.carryus.data.entitiy.response.StoreDetailInfoResponse
import com.sookmyung.carryus.data.entitiy.response.StoreDetailReviewResponse
import com.sookmyung.carryus.data.entitiy.response.StoreReservationResponse
import com.sookmyung.carryus.data.entitiy.response.StoreReservationTimeResponse
import retrofit2.http.GET
import retrofit2.http.POST
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

    @GET("/stores/{storeId}/reservation/time/info")
    suspend fun getStoreReservationTime(
        @Path("storeId") storeId: Int,
        @Query("date") date: String,
        @Query("extraSmallCount") extraSmallCount: Int,
        @Query("smallCount") smallCount: Int,
        @Query("largeCount") largeCount: Int,
        @Query("extraLargeCount") extraLargeCount: Int
    ): BaseResponse<StoreReservationTimeResponse>

    @POST("/stores/{storeId}/reservation")
    suspend fun postStoreReservationRequest(
        @Path("storeId") storeId: Int,
        storeReservationRequest: StoreReservationRequest
    ): BaseResponse<StoreReservationResponse>
}
