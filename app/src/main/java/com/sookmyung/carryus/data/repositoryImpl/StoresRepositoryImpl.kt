package com.sookmyung.carryus.data.repositoryImpl

import com.sookmyung.carryus.data.source.StoresDataSource
import com.sookmyung.carryus.domain.entity.StoreDetail
import com.sookmyung.carryus.domain.repository.StoresRepository
import javax.inject.Inject

class StoresRepositoryImpl @Inject constructor(
    private val storesDataSource: StoresDataSource
) : StoresRepository {
    override suspend fun getStoreDetailInfo(storeId: Int): Result<StoreDetail> = runCatching {
        storesDataSource.getStoreDetailInfo(storeId)
    }.mapCatching { response ->
        response.data?.toStoreDetail() ?: StoreDetail()
    }
}
