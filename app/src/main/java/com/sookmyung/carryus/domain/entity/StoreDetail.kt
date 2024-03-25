package com.sookmyung.carryus.domain.entity

data class StoreDetail(
    val storeId: Int = 0,
    val storeImgUrl: String = "",
    val storeName: String = "",
    val storeLocation: String = "",
    val closedDay: String = "",
    val openingHour: String = "",
    val storePhoneNumber: String = "",
    val baggageTypeInfoList: List<BaggageTypeInfo> = listOf(BaggageTypeInfo("20인치 미만",0,0),BaggageTypeInfo("20인치",0,0)
    ,BaggageTypeInfo("24인치",0,0),BaggageTypeInfo("28인치 이상",0,0))
)
