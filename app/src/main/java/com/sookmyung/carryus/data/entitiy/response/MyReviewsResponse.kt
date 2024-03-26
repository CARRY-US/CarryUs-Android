package com.sookmyung.carryus.data.entitiy.response

import com.sookmyung.carryus.domain.entity.MyReviews
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyReviewsResponse (
    @SerialName("reviewId")
    val reviewId: Int,
    @SerialName("storeName")
    val storeName: String,
    @SerialName("reviewRating")
    val reviewRating: Double,
    @SerialName("reviewContent")
    val reviewContent: String,
){
    fun toMyReviews() = MyReviews(
        reviewId = this.reviewId,
        storeName = this.storeName,
        reviewRating = this.reviewRating,
        reviewContent = this.reviewContent
    )
}
