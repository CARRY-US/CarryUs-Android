package com.sookmyung.carryus.domain.repository

import com.sookmyung.carryus.domain.entity.StoreSearchResult

interface SearchRepository {
    suspend fun getCityBaseStoreList(
        word: String
    ): Result<List<StoreSearchResult>>
}
