package com.sookmyung.carryus.domain.entity

data class StoreDetail(
    val storeId: Int = 0,
    val storeImgUrl: String = "",
    val storeName: String = "",
    val storeLocation: String = "",
    val closedDay: String = "",
    val openingHour: String = "",
    val storePhoneNumber: String = "",
    val baggageTypeInfoList: List<BaggageTypeInfo> = emptyList()
)
