package com.sookmyung.carryus.domain.entity

data class StoreReview(
    val reviewId: Int,
    val memberName: String,
    val reviewCreatedAt: String,
    val reviewRating: Double,
    val reviewText: String
)
