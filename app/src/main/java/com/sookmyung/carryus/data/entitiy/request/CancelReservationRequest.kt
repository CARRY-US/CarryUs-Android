package com.sookmyung.carryus.data.entitiy.request

import kotlinx.serialization.Serializable

@Serializable
data class CancelReservationRequest(
    val reservationId: Int,
    val cancelReason: String
)
