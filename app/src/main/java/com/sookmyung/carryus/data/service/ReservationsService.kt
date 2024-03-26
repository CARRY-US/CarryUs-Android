package com.sookmyung.carryus.data.service

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.request.CancelReservationRequest
import com.sookmyung.carryus.data.entitiy.request.ReviewRequest
import com.sookmyung.carryus.data.entitiy.response.CancelReservationResponse
import com.sookmyung.carryus.data.entitiy.response.ReservationDetailResponse
import com.sookmyung.carryus.data.entitiy.response.ReservationListResponse
import com.sookmyung.carryus.data.entitiy.response.ReviewResponse
import com.sookmyung.carryus.domain.entity.ReservationDetail
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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

    @POST("reservations")
    suspend fun postCancelReservation(
        @Body cancelReservationRequest: CancelReservationRequest
    ) : BaseResponse<CancelReservationResponse>

    @POST("reservations/{reservationId}/review")
    suspend fun postReview(
        @Path("reservationId") reservationId: Int, @Body reviewRequest: ReviewRequest
    ) : BaseResponse<ReviewResponse>
}
