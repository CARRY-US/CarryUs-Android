package com.sookmyung.carryus.data.repositoryImpl

import com.sookmyung.carryus.data.entitiy.request.CancelReservationRequest
import com.sookmyung.carryus.data.entitiy.request.ReviewRequest
import com.sookmyung.carryus.data.source.ReservationsDataSource
import com.sookmyung.carryus.domain.entity.ReservationDetail
import com.sookmyung.carryus.domain.entity.ReservationList
import com.sookmyung.carryus.domain.repository.ReservationsRepository
import javax.inject.Inject

class ReservationsRepositoryImpl @Inject constructor(
    private val reservationsDataSource: ReservationsDataSource
): ReservationsRepository {
    override suspend fun getReservationList(
        status: String
    ) : Result<List<ReservationList>> = runCatching {
        reservationsDataSource.getReservationList(status)
    }.mapCatching { response -> response.data?.map { it.toReservationList() } ?: emptyList()
    }

    override suspend fun getReservationDetail(
        reservationId: Int
    ) : Result<ReservationDetail> = runCatching {
        reservationsDataSource.getReservationDetail(reservationId)
    }.mapCatching { response -> response.data?.toReservationDetail() ?: ReservationDetail()
    }

    override suspend fun postCancelReservation(
        cancelReservationRequest: CancelReservationRequest
    ) : Result<Unit> = runCatching {
        reservationsDataSource.postCancelReservation(cancelReservationRequest)
    }.mapCatching { Unit }

    override suspend fun postReview(
        reservationId: Int, reviewRequest: ReviewRequest
    ) : Result<Unit> = runCatching {
        reservationsDataSource.postReview(reservationId, reviewRequest)
    }.mapCatching { Unit }
}
