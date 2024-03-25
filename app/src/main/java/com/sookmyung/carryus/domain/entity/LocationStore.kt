package com.sookmyung.carryus.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationStore(
    val storeId: Int,
    val storeImgUrl: String,
    val storeName: String,
    val storeLocation: String,
    val storeReviewCount: String,
    val storeRatingAverage: String
) : Parcelable
