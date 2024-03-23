package com.sookmyung.carryus.data.service

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.response.ReservationDetailResponse
import com.sookmyung.carryus.data.entitiy.response.ReservationListResponse
import com.sookmyung.carryus.domain.entity.ReservationDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReservationsService {
    @GET("reservations")
    suspend fun getReservationList(
        @Query("status") status: String
    ) : BaseResponse<List<ReservationListResponse>>

    @GET("reservations/{reservationId}")
    suspend fun getReservationDetail(
        @Path("reservationId") reservationId: Int
    ) : BaseResponse<ReservationDetailResponse>
}
