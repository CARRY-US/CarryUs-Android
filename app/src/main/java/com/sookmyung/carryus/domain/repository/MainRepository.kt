package com.sookmyung.carryus.domain.repository

import com.sookmyung.carryus.domain.entity.LocationStore
import com.sookmyung.carryus.domain.entity.StoreSearchResult

interface MainRepository {
    suspend fun getUserLocationStoreList(
        xMin: Double,
        xMax: Double,
        yMin: Double,
        yMax: Double
    ): Result<List<StoreSearchResult>>

    suspend fun getLocationStoreList(
        latitude: Double,
        longitude: Double
    ): Result<List<LocationStore>>
}
