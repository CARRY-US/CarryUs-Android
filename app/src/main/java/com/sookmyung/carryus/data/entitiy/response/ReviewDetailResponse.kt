package com.sookmyung.carryus.data.entitiy.response

import com.sookmyung.carryus.domain.entity.ReviewDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewDetailResponse(
    @SerialName("reviewId")
    val reviewId: Int,
    @SerialName("reviewRating")
    val reviewRating: Double,
    @SerialName("reviewContent")
    val reviewContent: String
){
    fun toReviewDetail() = ReviewDetail(
        reviewId = this.reviewId,
        reviewRating = this.reviewRating,
        reviewContent = this.reviewContent
    )
}
