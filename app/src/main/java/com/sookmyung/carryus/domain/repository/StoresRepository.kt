package com.sookmyung.carryus.domain.repository

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
}
