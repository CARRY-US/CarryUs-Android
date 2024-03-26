package com.sookmyung.carryus.data.entitiy.response

import com.sookmyung.carryus.domain.entity.ReservationId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreReservationResponse(
    @SerialName("reservationId")
    val reservationId: Int,
) {
    fun toReservationId(): ReservationId = ReservationId(
        reservationId = this.reservationId
    )
}
