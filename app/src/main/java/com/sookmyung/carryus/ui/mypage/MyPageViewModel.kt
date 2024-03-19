package com.sookmyung.carryus.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.MyReviews

class MyPageViewModel : ViewModel(){
    private val _navigateToDetail = MutableLiveData<MyReviews>()
    val navigateToDetail: LiveData<MyReviews>
        get() = _navigateToDetail

    fun onReservationItemClick(myReviews: MyReviews) {
        _navigateToDetail.value = myReviews
    }

    fun onNavigationComplete() {
        _navigateToDetail.value = null
    }
}
