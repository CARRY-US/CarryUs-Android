package com.sookmyung.carryus.domain.repository

import com.sookmyung.carryus.data.entitiy.request.CancelReservationRequest
import com.sookmyung.carryus.data.entitiy.request.ReviewRequest
import com.sookmyung.carryus.domain.entity.ReservationDetail
import com.sookmyung.carryus.domain.entity.ReservationList

interface ReservationsRepository {
    suspend fun getReservationList(
        status: String
    ): Result<List<ReservationList>>

    suspend fun getReservationDetail(
        reservationId: Int
    ): Result<ReservationDetail>

    suspend fun postCancelReservation(
        cancelReservationRequest: CancelReservationRequest
    ): Result<Unit>

    suspend fun postReview(
        reservationId: Int, reviewRequest: ReviewRequest
    ): Result<Unit>
}
