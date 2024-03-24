package com.sookmyung.carryus.data.repositoryImpl

import com.sookmyung.carryus.data.source.MainDataSource
import com.sookmyung.carryus.domain.entity.LocationStore
import com.sookmyung.carryus.domain.entity.StoreSearchResult
import com.sookmyung.carryus.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource
) : MainRepository {
    override suspend fun getUserLocationStoreList(
        xMin: Double,
        xMax: Double,
        yMin: Double,
        yMax: Double
    ): Result<List<StoreSearchResult>> = runCatching {
        mainDataSource.getUserLocationStoreList(xMin, xMax, yMin, yMax)
    }.mapCatching { response ->
        response.data?.map { it.toStoreSearchResult() } ?: emptyList()
    }

    override suspend fun getLocationStoreList(
        latitude: Double,
        longitude: Double
    ): Result<List<LocationStore>> = runCatching {
        mainDataSource.getLocationStoreList(latitude, longitude)
    }.mapCatching { response ->
        response.data?.map { it.toLocationStore() } ?: emptyList()
    }
}
