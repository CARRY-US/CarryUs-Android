package com.sookmyung.carryus.data.source

import com.sookmyung.carryus.data.entitiy.BaseResponse
import com.sookmyung.carryus.data.entitiy.request.CancelReservationRequest
import com.sookmyung.carryus.data.entitiy.request.ReviewRequest
import com.sookmyung.carryus.data.entitiy.response.CancelReservationResponse
import com.sookmyung.carryus.data.entitiy.response.ReservationDetailResponse
import com.sookmyung.carryus.data.entitiy.response.ReservationListResponse
import com.sookmyung.carryus.data.entitiy.response.ReviewResponse
import com.sookmyung.carryus.data.service.ReservationsService
import javax.inject.Inject

class ReservationsDataSource @Inject constructor(private val reservationsService: ReservationsService) {
    suspend fun getReservationList(status: String) : BaseResponse<List<ReservationListResponse>> =
        reservationsService.getReservationList(status)

    suspend fun getReservationDetail(reservationId: Int) : BaseResponse<ReservationDetailResponse> =
        reservationsService.getReservationDetail(reservationId)

    suspend fun postCancelReservation(cancelReservationRequest: CancelReservationRequest) : BaseResponse<CancelReservationResponse> =
        reservationsService.postCancelReservation(cancelReservationRequest)

    suspend fun postReview(reservationId: Int, reviewRequest: ReviewRequest) : BaseResponse<ReviewResponse> =
        reservationsService.postReview(reservationId, reviewRequest)

}
