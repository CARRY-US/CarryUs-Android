package com.sookmyung.carryus.data.repositoryImpl

import com.sookmyung.carryus.data.source.ReservationDataSource
import com.sookmyung.carryus.domain.entity.UserDefaultInfo
import com.sookmyung.carryus.domain.repository.ReservationRepository
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val reservationDataSource: ReservationDataSource
) : ReservationRepository {
    override suspend fun getUserDefaultInfo(): Result<UserDefaultInfo> = runCatching {
        reservationDataSource.getUserDefaultInfo()
    }.mapCatching { response ->
        response.data?.toUserDefaultInfo() ?: UserDefaultInfo()
    }
}
