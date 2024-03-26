package com.sookmyung.carryus.data.source

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.response.UserDefaultInfoResponse
import com.sookmyung.carryus.data.service.ReservationService
import javax.inject.Inject

class ReservationDataSource @Inject constructor(
    private val reservationService: ReservationService
) {
    suspend fun getUserDefaultInfo(): BaseResponse<UserDefaultInfoResponse> =
        reservationService.getUserDefaultInfo()
}
