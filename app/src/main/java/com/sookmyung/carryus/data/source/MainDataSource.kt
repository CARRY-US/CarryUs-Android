package com.sookmyung.carryus.data.source

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.response.UserLocationStoreResponse
import com.sookmyung.carryus.data.service.MainService
import javax.inject.Inject

class MainDataSource @Inject constructor(
    private val mainService: MainService
) {
    suspend fun getUserLocationStoreList(
        xMin: Double,
        xMax: Double,
        yMin: Double,
        yMax: Double
    ): BaseResponse<List<UserLocationStoreResponse>> =
        mainService.getUserLocationStoreList(xMin, xMax, yMin, yMax)
}
