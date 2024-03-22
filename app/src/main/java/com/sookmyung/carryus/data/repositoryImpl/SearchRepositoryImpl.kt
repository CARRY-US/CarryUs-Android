package com.sookmyung.carryus.data.repositoryImpl

import com.sookmyung.carryus.data.source.SearchDataSource
import com.sookmyung.carryus.domain.entity.StoreSearchResult
import com.sookmyung.carryus.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource
) : SearchRepository {
    override suspend fun getCityBaseStoreList(
        word: String
    ): Result<List<StoreSearchResult>> = runCatching {
        searchDataSource.getCityBaseStoreList(word)
    }.mapCatching { response ->
        response.data?.map { it.toStoreSearchResult() } ?: emptyList()
    }
}
