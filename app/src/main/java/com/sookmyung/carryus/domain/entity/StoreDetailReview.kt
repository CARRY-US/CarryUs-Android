package com.sookmyung.carryus.domain.entity

data class StoreDetailReview(
    val reviewRatingAverage: Double = 0.0,
    val reviewList: List<StoreReview> = emptyList()
)
