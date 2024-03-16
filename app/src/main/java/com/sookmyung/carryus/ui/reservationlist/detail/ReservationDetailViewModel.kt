package com.sookmyung.carryus.ui.reservationlist.detail

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.R
import com.sookmyung.carryus.domain.entity.ReservationDetailResponse
import com.sookmyung.carryus.domain.entity.ReservationStatus

class ReservationDetailViewModel : ViewModel(){
    val showDialog = MutableLiveData<Boolean>()
    private val _reservationDetailLiveData = MutableLiveData<ReservationDetailResponse>()

    val reservationDetailLiveData: LiveData<ReservationDetailResponse> = _reservationDetailLiveData

    private lateinit var context: Context

    fun toggleDialog() {
        showDialog.value = !(showDialog.value ?: false)
    }

    fun setReservationDetail(reservationDetail: ReservationDetailResponse) {
        _reservationDetailLiveData.value = reservationDetail
    }

    fun setContext(context: Context) {
        this.context = context
    }

    fun onButtonClick() {
        val reservationStatus = reservationDetailLiveData.value?.reservationType ?: ""
        when (reservationStatus) {
            ReservationStatus.ACCEPTED_STRING -> {
                toggleDialog()
            }
            ReservationStatus.WAITING_STRING -> {
                toggleDialog()
            }
            ReservationStatus.COMPLETED_STRING -> {
                navigateToWriteReview()
            }
            else -> {
            }
        }
    }

    fun navigateToWriteReview() {
        Log.d("ReservationDetailViewModel", "navigateToWriteReview")
//        val intent = Intent(context, ::class.java)
//        context.startActivity(intent)
    }

}
