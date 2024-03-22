package com.sookmyung.carryus.data.service

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.response.UserLocationStoreResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/main/search/stores")
    suspend fun getCityBaseStoreList(
        @Query("word") word: String
    ): BaseResponse<List<UserLocationStoreResponse>>
}
