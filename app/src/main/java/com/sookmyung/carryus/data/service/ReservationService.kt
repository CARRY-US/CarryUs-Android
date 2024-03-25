package com.sookmyung.carryus.data.service

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.response.UserDefaultInfoResponse
import retrofit2.http.GET

interface ReservationService {
    @GET("/reservations/my/info")
    suspend fun getUserDefaultInfo(): BaseResponse<UserDefaultInfoResponse>
}
