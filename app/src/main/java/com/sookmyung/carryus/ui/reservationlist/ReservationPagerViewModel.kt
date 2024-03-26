package com.sookmyung.carryus.ui.reservationlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.domain.entity.ReservationList
import com.sookmyung.carryus.domain.usecase.reservation.GetReservationListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ReservationPagerViewModel @Inject constructor(
    private val getReservationListUseCase: GetReservationListUseCase
) : ViewModel() {
    private val _locationStoreList = MutableLiveData<List<ReservationList>>()
    val locationStoreList: LiveData<List<ReservationList>>
        get() = _locationStoreList

    private val _navigateToDetail = MutableLiveData<ReservationList>()
    val navigateToDetail: LiveData<ReservationList>
        get() = _navigateToDetail

    fun onReservationItemClick(reservationList: ReservationList) {
        _navigateToDetail.value = reservationList
    }

    fun onNavigationComplete() {
        _navigateToDetail.value = null
    }

    fun setReservationList(status: String) {
        viewModelScope.launch {
            getReservationListUseCase(status)
                .onSuccess { response ->
                    _locationStoreList.value = response
                }.onFailure { throwable ->
                    Timber.e("서버 통신 실패 -> ${throwable.message}")
                }
        }
    }
}
