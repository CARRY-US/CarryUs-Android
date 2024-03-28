package com.sookmyung.carryus.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReservationList(
    val reservationId: Int = 0,
    val storeImgUrl: String = "",
    val storeName: String = "",
    val storeLocation: String = "",
    val reservationDate: String = "",
    val isReviewExist: Boolean = false
) : Parcelable
