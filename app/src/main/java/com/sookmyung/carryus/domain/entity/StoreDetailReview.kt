package com.sookmyung.carryus.domain.entity

data class StoreDetailReview(
    val reviewRatingAverage: Double,
    val reviewList: List<StoreReview>
)
