package com.sookmyung.carryus.domain.entity

data class StoreDetail(
    val storeId: Int,
    val storeImgUrl: String,
    val storeName: String,
    val storeLocation: String,
    val closedDay: String,
    val openingHours: String,
    val storePhoneNumber: String,
    val baggageTypeInfoList: List<BaggageTypeInfo>
)