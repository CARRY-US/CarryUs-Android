package com.sookmyung.carryus.data.source

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.request.StoreReservationRequest
import com.sookmyung.carryus.data.entitiy.response.StoreDetailInfoResponse
import com.sookmyung.carryus.data.entitiy.response.StoreDetailReviewResponse
import com.sookmyung.carryus.data.entitiy.response.StoreReservationResponse
import com.sookmyung.carryus.data.entitiy.response.StoreReservationTimeResponse
import com.sookmyung.carryus.data.service.StoresService
import javax.inject.Inject

class StoresDataSource @Inject constructor(
    private val storesService: StoresService
) {
    suspend fun getStoreDetailInfo(
        storeId: Int
    ): BaseResponse<StoreDetailInfoResponse> =
        storesService.getStoreDetailInfo(storeId)

    suspend fun getStoreDetailReview(
        storeId: Int
    ): BaseResponse<StoreDetailReviewResponse> = storesService.getStoreDetailReview(storeId)

    suspend fun getStoreReservationTime(
        storeId: Int,
        date: String,
        extraSmallCount: Int,
        smallCount: Int,
        largeCount: Int,
        extraLargeCount: Int
    ): BaseResponse<StoreReservationTimeResponse> = storesService.getStoreReservationTime(
        storeId, date, extraSmallCount, smallCount, largeCount, extraLargeCount
    )

    suspend fun postStoreReservationRequest(
        storeId: Int,
        extraSmallCount: Int,
        smallCount: Int,
        largeCount: Int,
        extraLargeCount: Int,
        reservationStartTime: String,
        reservationEndTime: String,
        memberName: String,
        memberPhoneNumber: String,
        memberRequestContent: String
    ): BaseResponse<StoreReservationResponse> = storesService.postStoreReservationRequest(
        storeId, StoreReservationRequest(
            extraSmallCount,
            smallCount,
            largeCount,
            extraLargeCount,
            reservationStartTime,
            reservationEndTime,
            memberName,
            memberPhoneNumber,
            memberRequestContent
        )
    )
}
