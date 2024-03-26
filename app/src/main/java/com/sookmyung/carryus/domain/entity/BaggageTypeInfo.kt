package com.sookmyung.carryus.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BaggageTypeInfo(
    val baggageType: String = "",
    val baggageCount: Int = 0,
    val baggagePrice: Int = 0
) : Parcelable
