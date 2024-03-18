package com.sookmyung.carryus.domain.entity

data class ReservationList(
    val reservationId: Int,
    val storeImgUrl: String,
    val storeName: String,
    val storeLocation: String,
    val reservationDate: String,
)
