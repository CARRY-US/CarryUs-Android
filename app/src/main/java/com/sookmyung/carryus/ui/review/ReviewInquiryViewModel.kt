package com.sookmyung.carryus.ui.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.ReservationList
import com.sookmyung.carryus.domain.entity.ReviewDetail

class ReviewInquiryViewModel: ViewModel(){
    private val _reviewDetailLiveData = MutableLiveData<ReviewDetail>()
    val reviewDetailLiveData: LiveData<ReviewDetail> = _reviewDetailLiveData

    private val _reservationListLiveData = MutableLiveData<ReservationList>()
    val reservationListLiveData: LiveData<ReservationList> = _reservationListLiveData

    fun setReviewDetail(reviewDetail: ReviewDetail) {
        _reviewDetailLiveData.value = reviewDetail
    }

    fun setReservationList(reservationList: ReservationList) {
        _reservationListLiveData.value = reservationList
    }
}
