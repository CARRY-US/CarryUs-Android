package com.sookmyung.carryus.domain.usecase

import com.sookmyung.carryus.domain.repository.StoresRepository
import javax.inject.Inject

class PostStoreReservationUseCase @Inject constructor(
    private val storesRepository: StoresRepository
) {
    suspend operator fun invoke(
        storeId: Int,
        extraSmallCount: Int,
        smallCount: Int,
        largeCount: Int,
        extraLargeCount: Int,
        reservationStartTime: String,
        reservationEndTime: String,
        memberName: String,
        memberPhoneNumber: String,
        memberRequestContent: String
    ) =
        storesRepository.postStoreReservationRequest(
            storeId,
            extraSmallCount,
            smallCount,
            largeCount,
            extraLargeCount,
            reservationStartTime,
            reservationEndTime,
            memberName,
            memberPhoneNumber,
            memberRequestContent
        )
}
