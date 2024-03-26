package com.sookmyung.carryus.data.entitiy.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreReservationRequest(
    @SerialName("extraSmallCount") val extraSmallCount: Int,
    @SerialName("smallCount") val smallCount: Int,
    @SerialName("largeCount") val largeCount: Int,
    @SerialName("extraLargeCount") val extraLargeCount: Int,
    @SerialName("reservationStartTime") val reservationStartTime: String,
    @SerialName("reservationEndTime") val reservationEndTime: String,
    @SerialName("memberName") val memberName: String,
    @SerialName("memberPhoneNumber") val memberPhoneNumber: String,
    @SerialName("memberRequestContent") val memberRequestContent: String,
)
