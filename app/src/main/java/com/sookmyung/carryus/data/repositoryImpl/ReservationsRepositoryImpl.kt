package com.sookmyung.carryus.data.repositoryImpl

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
    }.mapCatching { response -> response.data?.toReservationDetail() ?: throw Exception("Data is null")
    }
}
