package com.sookmyung.carryus.domain.repository

import com.sookmyung.carryus.domain.entity.ReservationDetail
import com.sookmyung.carryus.domain.entity.ReservationList

interface ReservationsRepository {
    suspend fun getReservationList(
        status: String
    ): Result<List<ReservationList>>

    suspend fun getReservationDetail(
        reservationId: Int
    ): Result<ReservationDetail>
}
