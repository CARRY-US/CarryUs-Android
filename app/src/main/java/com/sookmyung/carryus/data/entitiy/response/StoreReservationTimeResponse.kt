package com.sookmyung.carryus.data.entitiy.response

import com.sookmyung.carryus.domain.entity.StoreReservationTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreReservationTimeResponse(
    @SerialName("availableTimeList") val availableTimeList: List<Boolean>
) {
    fun toStoreReservationTime(): StoreReservationTime = StoreReservationTime(
        availableTimeList = this.availableTimeList
    )
}
