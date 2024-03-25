package com.sookmyung.carryus.data.source

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.response.UserLocationStoreResponse
import com.sookmyung.carryus.data.service.SearchService
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    private val searchService: SearchService
) {
    suspend fun getCityBaseStoreList(
        word: String
    ): BaseResponse<List<UserLocationStoreResponse>> =
        searchService.getCityBaseStoreList(word)
}
