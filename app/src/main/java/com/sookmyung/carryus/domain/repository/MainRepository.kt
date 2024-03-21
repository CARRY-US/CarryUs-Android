package com.sookmyung.carryus.domain.repository

import com.sookmyung.carryus.domain.entity.StoreSearchResult
import retrofit2.http.Query

interface MainRepository {
    suspend fun getUserLocationStoreList(
        xMin: Double,
        xMax: Double,
        yMin: Double,
        yMax: Double
    ): Result<List<StoreSearchResult>>
}
