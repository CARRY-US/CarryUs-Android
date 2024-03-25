package com.sookmyung.carryus.domain.repository

import com.sookmyung.carryus.domain.entity.StoreDetail
import com.sookmyung.carryus.domain.entity.StoreDetailReview

interface StoresRepository {
    suspend fun getStoreDetailInfo(
        storeId: Int
    ): Result<StoreDetail>

    suspend fun getStoreDetailReview(
        storeId: Int
    ): Result<StoreDetailReview>
}
