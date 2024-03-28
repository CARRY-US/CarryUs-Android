package com.sookmyung.carryus.data.entitiy.response

import com.sookmyung.carryus.domain.entity.ReservationList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReservationListResponse(
    @SerialName("reservationId")
    val reservationId: Int,
    @SerialName("storeImgUrl")
    val storeImgUrl: String,
    @SerialName("storeName")
    val storeName: String,
    @SerialName("storeLocation")
    val storeLocation: String,
    @SerialName("reservationInfo")
    val reservationInfo: String,
    @SerialName("isReviewExist")
    val isReviewExist: Boolean
){
    fun toReservationList(): ReservationList = ReservationList(
        reservationId = this.reservationId,
        storeImgUrl = this.storeImgUrl,
        storeName = this.storeName,
        storeLocation = this.storeLocation,
        reservationDate = this.reservationInfo,
        isReviewExist = this.isReviewExist
    )
}
