package com.sookmyung.carryus.ui.reservationlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.ReservationList

class ReservationPagerViewModel: ViewModel() {
    private val _navigateToDetail = MutableLiveData<ReservationList>()
    val navigateToDetail: LiveData<ReservationList>
        get() = _navigateToDetail

    fun onReservationItemClick(reservationList: ReservationList) {
        _navigateToDetail.value = reservationList
    }

    fun onNavigationComplete() {
        _navigateToDetail.value = null
    }
}
