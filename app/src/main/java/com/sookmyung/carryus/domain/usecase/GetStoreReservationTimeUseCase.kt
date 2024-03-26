package com.sookmyung.carryus.domain.usecase

import com.sookmyung.carryus.domain.repository.StoresRepository
import javax.inject.Inject

class GetStoreReservationTimeUseCase @Inject constructor(
    private val storesRepository: StoresRepository
) {
    suspend operator fun invoke(
        storeId: Int,
        date: String,
        extraSmallCount: Int,
        smallCount: Int,
        largeCount: Int,
        extraLargeCount: Int
    ) =
        storesRepository.getStoreReservationTime(
            storeId,
            date,
            extraSmallCount,
            smallCount,
            largeCount,
            extraLargeCount
        )
}
