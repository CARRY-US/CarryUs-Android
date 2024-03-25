package com.sookmyung.carryus.data.source

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.response.StoreDetailInfoResponse
import com.sookmyung.carryus.data.entitiy.response.StoreDetailReviewResponse
import com.sookmyung.carryus.data.service.StoresService
import javax.inject.Inject

class StoresDataSource @Inject constructor(
    private val storesService: StoresService
) {
    suspend fun getStoreDetailInfo(
        storeId: Int
    ): BaseResponse<StoreDetailInfoResponse> =
        storesService.getStoreDetailInfo(storeId)

    suspend fun getStoreDetailReview(
        storeId: Int
    ): BaseResponse<StoreDetailReviewResponse> = storesService.getStoreDetailReview(storeId)
}
