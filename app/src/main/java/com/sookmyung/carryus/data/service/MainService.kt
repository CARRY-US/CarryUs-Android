package com.sookmyung.carryus.data.service

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.response.LocationStoreResponse
import com.sookmyung.carryus.data.entitiy.response.UserLocationStoreResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {
    @GET("main/location/stores")
    suspend fun getUserLocationStoreList(
        @Query("xMin") xMin: Double,
        @Query("xMax") xMax: Double,
        @Query("yMin") yMin: Double,
        @Query("yMax") yMax: Double
    ): BaseResponse<List<UserLocationStoreResponse>>

    @GET("main/stores")
    suspend fun getLocationStoreList(
        @Query("x") latitude: Double,
        @Query("y") longitude: Double
    ): BaseResponse<List<LocationStoreResponse>>
}
