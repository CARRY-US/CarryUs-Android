package com.sookmyung.carryus.ui.search.reservationrequest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.domain.entity.BaggageTypeInfo
import com.sookmyung.carryus.domain.entity.StoreReservationTime
import com.sookmyung.carryus.domain.entity.Suitcase
import com.sookmyung.carryus.domain.entity.Time
import com.sookmyung.carryus.domain.usecase.GetStoreReservationTimeUseCase
import com.sookmyung.carryus.domain.usecase.GetUserDefaultInfoUseCase
import com.sookmyung.carryus.domain.usecase.PostStoreReservationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ReservationRequestViewModel @Inject constructor(
    val getUserDefaultInfoUseCase: GetUserDefaultInfoUseCase,
    val getStoreReservationTimeUseCase: GetStoreReservationTimeUseCase,
    val postStoreReservationUseCase: PostStoreReservationUseCase
) : ViewModel() {
    private val _reservationRequestAvailableTimeList: MutableLiveData<StoreReservationTime> =
        MutableLiveData()
    val reservationRequestAvailableTimeList: LiveData<StoreReservationTime> get() = _reservationRequestAvailableTimeList

    private val _suitCase: MutableLiveData<Suitcase> = MutableLiveData(Suitcase(0, 0, 0, 0))
    val suitCase: LiveData<Suitcase> get() = _suitCase

    private val _isSendBtnClickable = MutableLiveData(false)
    val isSendBtnClickable: LiveData<Boolean> get() = _isSendBtnClickable

    private val _isCheckBtnClickable = MutableLiveData(false)
    val isCheckBtnClickable: LiveData<Boolean> get() = _isCheckBtnClickable

    private val _amount = MutableLiveData(0)
    val amount: LiveData<Int> get() = _amount

    private val _storeId: MutableLiveData<Int> = MutableLiveData()
    private val suitCaseFee = mutableListOf<Int>(0, 0, 0, 0)
    private val reservationTime: MutableList<Int> = mutableListOf()
    private var selectedDate: String = ""
    val reservationRequestTimeList: MutableList<Time> = mutableListOf()
    var prevStartTime: Int = 0
    var prevEndTime: Int = 0
    var startTime: Int = 0
    var endTime: Int = 0
    val todayDate: Long = getFormattedDateLong()
    val name = MutableLiveData("")
    val phoneNumber = MutableLiveData("")
    val others = MutableLiveData("")

    init {
        initReservationRequestTimeList()
        getFormattedDateString()
        getUserDefault()
    }

    private fun initReservationRequestTimeList() {
        repeat(24) {
            val time = Time(timeId = it + 1)
            reservationRequestTimeList.add(time)
        }
    }

    fun getFormattedDateString(date: LocalDate = LocalDate.now()) {
        selectedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    private fun getUserDefault() {
        viewModelScope.launch {
            getUserDefaultInfoUseCase()
                .onSuccess { response ->
                    name.value = response.memberName
                    phoneNumber.value = response.memberPhoneNumber
                }
                .onFailure { throwable ->
                    Timber.e("서버 통신 실패 -> ${throwable.message}")
                }
        }
    }

    fun postReservation() {
        viewModelScope.launch {
            postStoreReservationUseCase(
                _storeId.value ?: 0,
                suitCase.value?.extraSmall ?: 0,
                suitCase.value?.small ?: 0,
                suitCase.value?.large ?: 0,
                suitCase.value?.extraLarge ?: 0,
                generateDateTimeString(selectedDate, startTime),
                generateDateTimeString(selectedDate, endTime),
                name.value ?: "",
                phoneNumber.value ?: "",
                others.value ?: ""
            ).onSuccess { response ->
                Timber.tag("reservationId").d("$response")
            }.onFailure { throwable ->
                Timber.e("서버 통신 실패 -> ${throwable.message}")
            }
        }
    }

    private fun generateDateTimeString(selectedDate: String, selectedTime: Int): String {
        val timeString = String.format("%02d:00:00", selectedTime)
        return "$selectedDate" + "T$timeString"
    }

    fun getReservationRequestTimeList() {
        viewModelScope.launch {
            getStoreReservationTimeUseCase(
                _storeId.value ?: 0,
                selectedDate,
                _suitCase.value?.extraSmall ?: 0,
                _suitCase.value?.small ?: 0,
                _suitCase.value?.large ?: 0,
                _suitCase.value?.extraLarge ?: 0,
            ).onSuccess { response ->
                _reservationRequestAvailableTimeList.value = response
            }.onFailure { throwable ->
                Timber.e("서버 통신 실패 -> ${throwable.message}")
            }
        }
    }

    fun getReservationRequest() {
        reservationRequestAvailableTimeList.value?.availableTimeList?.forEachIndexed { index, bool ->
            val hourFormatted = String.format("%02d", index)
            reservationRequestTimeList[index] =
                reservationRequestTimeList[index].copy(
                    timeId = index,
                    hour = hourFormatted,
                    minute = "00",
                    available = bool,
                    select = false
                )
        }
    }

    fun itemClick(pos: Int) {
        when {
            reservationTime.isEmpty() -> handleFirstItemClick(pos)
            reservationTime.size == 1 -> handleSecondItemClick(pos)
            else -> handleBothItemClicks(pos)
        }
        selectTimeRange()
    }


    private fun handleFirstItemClick(pos: Int) {
        reservationTime.add(pos)
        prevStartTime = pos
        startTime = pos
        prevEndTime = pos
        endTime = pos
    }

    private fun handleSecondItemClick(pos: Int) {
        if (pos <= reservationTime.min()) {
            removeTimeRange()
            reservationTime.removeAll { it < 24 }
            reservationTime.add(pos)
            prevStartTime = startTime
            startTime = pos
            prevEndTime = prevStartTime
            endTime = pos
        } else {
            reservationTime.add(pos)
            prevEndTime = pos
            endTime = pos
        }
    }

    private fun handleBothItemClicks(pos: Int) {
        removeTimeRange()
        reservationTime.removeAll { it < 24 }
        reservationTime.add(pos)
        prevStartTime = startTime
        startTime = pos
        prevEndTime = endTime
        endTime = pos
    }

    private fun removeTimeRange() {
        val min = prevStartTime.coerceAtMost(startTime)
        val max = prevEndTime.coerceAtLeast(endTime)
        for (temp in min..max) {
            reservationRequestTimeList[temp] = reservationRequestTimeList[temp].copy(select = false)
        }
    }

    private fun selectTimeRange() {
        for (temp in startTime..endTime) {
            reservationRequestTimeList[temp] = reservationRequestTimeList[temp].copy(select = true)
        }
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

    fun checkIsCheckBtnClickable() {
        val suit = _suitCase.value ?: Suitcase(0, 0, 0, 0)
        val sum = suit.run { extraSmall + small + large + extraLarge }
        _isCheckBtnClickable.value = sum > 0
    }

    private fun getFormattedDateLong(): Long {
        val currentDate = Date(System.currentTimeMillis())
        return currentDate.time
    }

    fun updateStoreId(storeId: Int) {
        _storeId.value = storeId
    }

    fun updateSearchSuitCaseFee(list: List<BaggageTypeInfo>) {
        for (i in 0 until minOf(4, list.size)) {
            suitCaseFee[i] = list[i].baggagePrice
        }
    }

    fun getAmount() {
        val suit = _suitCase.value ?: Suitcase(0, 0, 0, 0)
        _amount.value =
            suit.run { extraSmall * suitCaseFee[0] + small * suitCaseFee[1] + large * suitCaseFee[2] + extraLarge * suitCaseFee[3] } * reservationTime.size
    }

    companion object {
        const val EXTRA_SMALL = 1
        const val SMALL = 2
        const val LARGE = 3
        const val EXTRA_LARGE = 4
    }
}
