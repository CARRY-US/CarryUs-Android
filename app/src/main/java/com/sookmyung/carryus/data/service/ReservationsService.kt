package com.sookmyung.carryus.data.service

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.response.ReservationListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ReservationsService {
    @GET("reservations")
    suspend fun getReservationList(
        @Query("status") status: String
    ) : BaseResponse<List<ReservationListResponse>>
}
