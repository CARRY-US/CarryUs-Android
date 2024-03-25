package com.sookmyung.carryus.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoreSearchResult(
    val storeId: Int = 0,
    val storeImgUrl: String = "",
    val storeName: String = "",
    val storeLocation: String = "",
    val storeReviewCount: String = "0",
    val storeRatingAverage: String = "0",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
) : Parcelable
