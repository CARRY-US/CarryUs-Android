package com.sookmyung.carryus.domain.repository

import com.sookmyung.carryus.data.entitiy.request.StoreReservationRequest
import com.sookmyung.carryus.domain.entity.ReservationId
import com.sookmyung.carryus.domain.entity.StoreDetail
import com.sookmyung.carryus.domain.entity.StoreDetailReview
import com.sookmyung.carryus.domain.entity.StoreReservationTime

interface StoresRepository {
    suspend fun getStoreDetailInfo(
        storeId: Int
    ): Result<StoreDetail>

    suspend fun getStoreDetailReview(
        storeId: Int
    ): Result<StoreDetailReview>

    suspend fun getStoreReservationTime(
        storeId: Int,
        date: String,
        extraSmallCount: Int,
        smallCount: Int,
        largeCount: Int,
        extraLargeCount: Int
    ): Result<StoreReservationTime>

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
    ):Result<ReservationId>
}
