package com.sookmyung.carryus.domain.entity

data class StoreInfo(
    val storeId: Int,
    val name: String,
    val location: String,
    val breakTime: String,
    val time: String,
    val call: String,
    val extraSmallFee: Int,
    val smallFee: Int,
    val largeFee: Int,
    val extraLargeFee: Int,
    val extraSmallCount: Int,
    val smallCount: Int,
    val largeCount: Int,
    val extraLargeCount: Int,
    val reviewScore: String
)
