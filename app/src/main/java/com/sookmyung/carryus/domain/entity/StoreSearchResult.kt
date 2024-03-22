package com.sookmyung.carryus.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoreSearchResult(
    val storeId: Int,
    val storeImgUrl: String,
    val storeName: String,
    val storeLocation: String,
    val storeReviewCount: String,
    val storeRatingAverage: String,
    val latitude: Double,
    val longitude: Double
) : Parcelable
