package com.sookmyung.carryus.ui.reservationlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.ReservationStatus

class ReservationListViewModel : ViewModel(){

    private val _tabItems: MutableLiveData<List<String>> = MutableLiveData()
    private val _position: MutableLiveData<Int> = MutableLiveData()

    val tabItems: LiveData<List<String>> get() = _tabItems
    val position: LiveData<Int> get() = _position

    companion object {
        private val TAB_ITEMS = listOf(
            ReservationStatus.ACCEPTED.status,
            ReservationStatus.WAITING.status,
            ReservationStatus.COMPLETED.status,
            ReservationStatus.CANCELED.status
        )
    }

    init {
        _tabItems.postValue(TAB_ITEMS)
    }
}
