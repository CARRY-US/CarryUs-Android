package com.sookmyung.carryus.data.entitiy.response

import com.sookmyung.carryus.domain.entity.ReservationDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReservationDetailResponse (
   @SerialName("reservationId")
    val reservationId: Int,
    @SerialName("storeId")
    val storeId: Int,
    @SerialName("storeImgUrl")
    val storeImgUrl: String,
    @SerialName("storeName")
    val storeName: String,
    @SerialName("reservationType")
    val reservationType: String,
    @SerialName("reservationDate")
    val reservationDate: String,
    @SerialName("reservationBaggageInfo")
    val reservationBaggageInfo: String,
    @SerialName("memberName")
    val memberName: String,
    @SerialName("memberPhoneNumber")
    val memberPhoneNumber: String,
    @SerialName("reservationRequest")
    val reservationRequest: String,
    @SerialName("reservationPayment")
    val reservationPayment: Int
){
    fun toReservationDetail() = ReservationDetail(
        reservationId = this.reservationId,
        storeId = this.storeId,
        storeImgUrl = this.storeImgUrl,
        storeName = this.storeName,
        reservationType = this.reservationType,
        reservationDate = this.reservationDate,
        reservationBaggageInfo = this.reservationBaggageInfo,
        memberName = this.memberName,
        memberPhoneNumber = this.memberPhoneNumber,
        reservationRequest = this.reservationRequest,
        reservationPayment = this.reservationPayment
    )
}
