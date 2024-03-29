package com.sookmyung.carryus.data.entitiy.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CancelReservationResponse(
    @SerialName("reservationId")
    val reservationId: Int,
)
