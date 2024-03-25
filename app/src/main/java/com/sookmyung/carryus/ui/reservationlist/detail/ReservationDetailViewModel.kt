package com.sookmyung.carryus.ui.reservationlist.detail

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.domain.entity.ReservationDetail
import com.sookmyung.carryus.domain.entity.ReservationStatus
import com.sookmyung.carryus.domain.usecase.reservation.GetReservationDetailUseCase
import com.sookmyung.carryus.ui.review.ReviewWriteActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationDetailViewModel @Inject constructor(
    private val getReservationDetailUseCase: GetReservationDetailUseCase
) : ViewModel(){
    val showDialog = MutableLiveData<Boolean>()

    private val _reservationDetailLiveData = MutableLiveData<ReservationDetail>()
    val reservationDetailLiveData: LiveData<ReservationDetail>
        get() = _reservationDetailLiveData

    private lateinit var context: Context

    fun toggleDialog() {
        showDialog.value = !(showDialog.value ?: false)
    }

    fun setContext(context: Context) {
        this.context = context
    }

    fun onButtonClick() {
        val reservationStatus = reservationDetailLiveData.value?.reservationType ?: ""
        when (reservationStatus) {
            ReservationStatus.ACCEPTED_STRING,ReservationStatus.WAITING_STRING -> {
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
        val intent = Intent(context, ReviewWriteActivity::class.java)
        intent.putExtra("reservation_id", reservationDetailLiveData.value?.reservationId ?: 0)
        context.startActivity(intent)
    }

    fun setReservationDetail(reservationId: Int) {
        viewModelScope.launch {
            getReservationDetailUseCase(reservationId)
                .onSuccess { response ->
                    val modifiedReservationDetail = response.copy(
                        reservationType =
                        when (response.reservationType) {
                            ReservationStatus.ACCEPTED.status -> ReservationStatus.ACCEPTED_STRING
                            ReservationStatus.CANCELED.status -> ReservationStatus.CANCELED_STRING
                            ReservationStatus.WAITING.status -> ReservationStatus.WAITING_STRING
                            ReservationStatus.COMPLETED.status -> ReservationStatus.COMPLETED_STRING
                            else -> ""
                        }
                    )
                    _reservationDetailLiveData.value = modifiedReservationDetail
                }.onFailure { throwable ->
                    Log.e("ReservationDetailViewModel", "서버 통신 실패 -> ${throwable.message}")
                }
        }
    }

}
