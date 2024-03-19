package com.sookmyung.carryus.ui.search.reservationrequest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.Time
import java.util.Random

class ReservationRequestViewModel : ViewModel() {
    val reservationRequestTimeList: MutableList<Time> = mutableListOf()
    private val _reservationRequestAvailableTimeList: MutableLiveData<List<Boolean>> =
        MutableLiveData()
    val reservationRequestAvailableTimeList: LiveData<List<Boolean>> get() = _reservationRequestAvailableTimeList

    init {
        initReservationRequestTimeList()
        getReservationRequest()
    }

    private fun initReservationRequestTimeList(){
        for (hour in 0..24) {
            reservationRequestTimeList.add(Time(hour, "%02d".format(hour), "00", false))
        }

    }

    private fun getReservationRequest() {
        _reservationRequestAvailableTimeList.value = generateRandomBooleanList(24)
        reservationRequestAvailableTimeList.value?.forEachIndexed { index, bool ->
            reservationRequestTimeList[index] = reservationRequestTimeList[index].copy(available = bool)
        }
    }

    //TODO server값 대신용 삭제할 것
    private fun generateRandomBooleanList(size: Int): List<Boolean> {
        return List(size) { Random().nextBoolean() }
    }

}
