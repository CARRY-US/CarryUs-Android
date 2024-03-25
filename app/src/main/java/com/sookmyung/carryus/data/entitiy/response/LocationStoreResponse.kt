package com.sookmyung.carryus.data.entitiy.response

import com.sookmyung.carryus.domain.entity.LocationStore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationStoreResponse(
    @SerialName("storeId") val storeId: Int,
    @SerialName("storeImgUrl") val storeImgUrl: String,
    @SerialName("storeName") val storeName: String,
    @SerialName("storeLocation") val storeLocation: String,
    @SerialName("storeReviewCount") val storeReviewCount: Int,
    @SerialName("storeRatingAverage") val storeRatingAverage: Double
) {
    fun toLocationStore(): LocationStore = LocationStore(
        storeId = this.storeId,
        storeImgUrl = this.storeImgUrl,
        storeName = this.storeName,
        storeLocation = this.storeLocation,
        storeReviewCount = this.storeReviewCount.toString(),
        storeRatingAverage = this.storeRatingAverage.toString()
    )
}
