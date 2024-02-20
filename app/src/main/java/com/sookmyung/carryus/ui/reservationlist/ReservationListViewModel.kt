package com.sookmyung.carryus.ui.reservationlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReservationListViewModel : ViewModel(){
    val tabItems: LiveData<List<String>> get() = _tabItems
    val position: LiveData<Int> get() = _position

    private val _tabItems: MutableLiveData<List<String>> = MutableLiveData()
    private val _position: MutableLiveData<Int> = MutableLiveData()

    companion object {
        private val TAB_ITEMS = listOf("수락", "대기", "완료", "취소")
    }

    init {
        _tabItems.postValue(TAB_ITEMS)
    }

    fun selectPosition(position: Int) {
        _position.postValue(position)
    }
}
