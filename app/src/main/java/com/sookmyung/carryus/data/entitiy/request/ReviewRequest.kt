package com.sookmyung.carryus.data.entitiy.request

import kotlinx.serialization.Serializable

@Serializable
data class ReviewRequest (
    val reviewRating: Double,
    val reviewContent: String
)
