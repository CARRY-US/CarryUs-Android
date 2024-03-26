package com.sookmyung.carryus.domain.entity

data class ReviewStoreInfo(
    val storeId: Int = 0,
    val storeName: String = "",
    val storeImgUrl: String = "",
    val storeLocation: String = "",
    val reservationInfo: String= ""
)
