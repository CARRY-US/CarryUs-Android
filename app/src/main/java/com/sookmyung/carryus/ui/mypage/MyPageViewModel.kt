package com.sookmyung.carryus.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.MyReviews
import com.sookmyung.carryus.domain.entity.ReservationStatus

class MyPageViewModel : ViewModel(){
    private val _navigateToDetail = MutableLiveData<MyReviews>()

    val showDialog = MutableLiveData<Boolean>()

    val navigateToDetail: LiveData<MyReviews>
        get() = _navigateToDetail

    fun onReservationItemClick(myReviews: MyReviews) {
        _navigateToDetail.value = myReviews
    }

    fun onNavigationComplete() {
        _navigateToDetail.value = null
    }

    fun toggleDialog() {
        showDialog.value = !(showDialog.value ?: false)
    }

    fun onButtonClick() {
        toggleDialog()

    }
}
