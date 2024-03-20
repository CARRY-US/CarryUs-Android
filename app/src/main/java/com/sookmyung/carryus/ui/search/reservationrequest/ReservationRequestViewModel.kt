package com.sookmyung.carryus.ui.search.reservationrequest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.Suitcase
import com.sookmyung.carryus.domain.entity.Time
import java.util.Date
import java.util.Random

class ReservationRequestViewModel : ViewModel() {
    val reservationRequestTimeList: MutableList<Time> = mutableListOf()
    private val _reservationRequestAvailableTimeList: MutableLiveData<List<Boolean>> =
        MutableLiveData()
    val reservationRequestAvailableTimeList: LiveData<List<Boolean>> get() = _reservationRequestAvailableTimeList

    private val _suitCase: MutableLiveData<Suitcase> = MutableLiveData(Suitcase(0, 0, 0, 0))
    val suitCase: LiveData<Suitcase> get() = _suitCase

    val todayDate: Long = getFormattedDateLong()
    val name = MutableLiveData("")
    val phoneNumber = MutableLiveData("")
    val others = MutableLiveData("")
    val reservationTime: MutableList<Int> = mutableListOf()

    private val _isSendBtnClickable = MutableLiveData(false)
    val isSendBtnClickable: LiveData<Boolean> get() = _isSendBtnClickable


    init {
        initReservationRequestTimeList()
        getReservationRequest()
    }

    private fun initReservationRequestTimeList() {
        for (hour in 0..24) {
            reservationRequestTimeList.add(
                Time(
                    hour,
                    "%02d".format(hour),
                    "00",
                    available = false,
                    select = false
                )
            )
        }

    }

    private fun getReservationRequest() {
        _reservationRequestAvailableTimeList.value = generateRandomBooleanList(24)
        reservationRequestAvailableTimeList.value?.forEachIndexed { index, bool ->
            reservationRequestTimeList[index] =
                reservationRequestTimeList[index].copy(available = bool)
        }
    }

    //TODO server값 대신용 삭제할 것
    private fun generateRandomBooleanList(size: Int): List<Boolean> {
        return List(size) { Random().nextBoolean() }
    }

    fun itemClick(pos: Int) {
        val currentTime = reservationRequestTimeList[pos]
        val updatedTime = currentTime.copy(select = !currentTime.select)

        if(updatedTime.select) reservationTime.add(pos)
        else reservationTime.remove(pos)

        reservationRequestTimeList[pos] = updatedTime
    }

    fun clickSuitCase(suitcase: Int, oper: Int) {
        val data = _suitCase.value
        when (suitcase) {
            EXTRA_SMALL -> {
                val newValue = data?.extraSmall?.plus(oper) ?: 0
                if (newValue >= 0) _suitCase.value = data?.copy(extraSmall = newValue)
            }

            SMALL -> {
                val newValue = data?.small?.plus(oper) ?: 0
                if (newValue >= 0) _suitCase.value = data?.copy(small = newValue)
            }

            LARGE -> {
                val newValue = data?.large?.plus(oper) ?: 0
                if (newValue >= 0) _suitCase.value = data?.copy(large = newValue)
            }

            EXTRA_LARGE -> {
                val newValue = data?.extraLarge?.plus(oper) ?: 0
                if (newValue >= 0) _suitCase.value = data?.copy(extraLarge = newValue)
            }
        }
    }

    fun clearSuitcase() {
        _suitCase.value = Suitcase(0, 0, 0, 0)
    }

    fun checkIsSendBtnClickable() {
        _isSendBtnClickable.value =
            !(name.value.isNullOrEmpty() || phoneNumber.value.isNullOrEmpty() || reservationTime.isEmpty())
    }

    private fun getFormattedDateLong(): Long {
        val currentDate = Date(System.currentTimeMillis())
        return currentDate.time
    }


    companion object {
        const val EXTRA_SMALL = 1
        const val SMALL = 2
        const val LARGE = 3
        const val EXTRA_LARGE = 4
    }
}
