package com.sookmyung.carryus.data.entitiy.response

import com.sookmyung.carryus.domain.entity.ReviewStoreInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewStoreInfoResponse(
    @SerialName("storeId")
    val storeId: Int,
    @SerialName("storeName")
    val storeName: String,
    @SerialName("storeImgUrl")
    val storeImgUrl: String,
    @SerialName("storeLocation")
    val storeLocation: String,
    @SerialName("reservationInfo")
    val reservationInfo: String
) {
    fun toReviewStoreInfo() = ReviewStoreInfo(
        storeId = this.storeId,
        storeName = this.storeName,
        storeImgUrl = this.storeImgUrl,
        storeLocation = this.storeLocation,
        reservationInfo = this.reservationInfo
    )
}
