package com.sookmyung.carryus.domain.repository

import com.sookmyung.carryus.domain.entity.StoreDetail

interface StoresRepository {
    suspend fun getStoreDetailInfo(
        storeId: Int
    ): Result<StoreDetail>
}
