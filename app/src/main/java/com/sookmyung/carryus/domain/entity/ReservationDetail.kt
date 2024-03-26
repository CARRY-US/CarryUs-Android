package com.sookmyung.carryus.domain.entity

data class ReservationDetail(
    val reservationId: Int = 0,
    val storeId: Int = 0,
    val storeImgUrl: String = "",
    val storeName: String = "",
    val reservationType: String = "",
    val reservationDate: String = "",
    val reservationBaggageInfo: String = "",
    val memberName: String = "",
    val memberPhoneNumber: String = "",
    val reservationRequest: String = "",
    val reservationPayment: Int =0
)
