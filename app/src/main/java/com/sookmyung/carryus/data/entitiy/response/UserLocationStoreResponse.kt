package com.sookmyung.carryus.data.entitiy.response

import com.sookmyung.carryus.domain.entity.StoreSearchResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserLocationStoreResponse(
    @SerialName("storeId") val storeId: Int,
    @SerialName("storeImgUrl") val storeImgUrl: String,
    @SerialName("storeName") val storeName: String,
    @SerialName("storeLocation") val storeLocation: String,
    @SerialName("storeReviewCount") val storeReviewCount: Int,
    @SerialName("storeRatingAverage") val storeRatingAverage: Double,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double
){
    fun toStoreSearchResult(): StoreSearchResult = StoreSearchResult(
        storeId = this.storeId,
        storeImgUrl = this.storeImgUrl,
        storeName = this.storeName,
        storeLocation = this.storeLocation,
        storeReviewCount = this.storeReviewCount.toString(),
        storeRatingAverage = this.storeRatingAverage.toString(),
        latitude = this.latitude,
        longitude = this.longitude
    )
}
