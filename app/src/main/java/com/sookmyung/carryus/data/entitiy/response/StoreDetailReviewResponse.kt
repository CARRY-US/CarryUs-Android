package com.sookmyung.carryus.data.entitiy.response

import com.sookmyung.carryus.domain.entity.StoreDetailReview
import com.sookmyung.carryus.domain.entity.StoreReview
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreDetailReviewResponse(
    @SerialName("reviewRatingAverage") val reviewRatingAverage: Double,
    @SerialName("reviewList") val reviewList: List<Review>
) {
    @Serializable
    data class Review(
        val reviewId: Int,
        val memberName: String,
        val reviewCreatedAt: String,
        val reviewRating: Double,
        val reviewText: String
    ) {
        fun toStoreReview(): StoreReview = StoreReview(
            reviewId = this.reviewId,
            memberName = this.memberName,
            reviewCreatedAt = this.reviewCreatedAt,
            reviewRating = this.reviewRating,
            reviewText = this.reviewText
        )
    }

    fun toStoreDetailReview(): StoreDetailReview = StoreDetailReview(
        reviewRatingAverage = this.reviewRatingAverage,
        reviewList = this.reviewList.map { it.toStoreReview() }
    )
}
