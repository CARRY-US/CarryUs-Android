package com.sookmyung.carryus.ui.reservationlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReservationDetailViewModel : ViewModel(){
    // 다이얼로그 표시 여부를 관리하는 LiveData
    val showDialog = MutableLiveData<Boolean>()

    // 다이얼로그 표시 여부를 토글하는 함수
    fun toggleDialog() {
        showDialog.value = !(showDialog.value ?: false)
    }
}
