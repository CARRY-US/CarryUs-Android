package com.sookmyung.carryus.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.domain.entity.MyProfile
import com.sookmyung.carryus.domain.entity.MyReviews
import com.sookmyung.carryus.domain.usecase.my.GetMyProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getMyProfileUseCase: GetMyProfileUseCase
): ViewModel(){
    private val _navigateToDetail = MutableLiveData<MyReviews>()

    private val _myProfile = MutableLiveData<MyProfile>()
    val myProfile: LiveData<MyProfile>
        get() = _myProfile

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

    fun getMyProfile() {
        viewModelScope.launch {
            getMyProfileUseCase()
                .onSuccess { response ->
                    _myProfile.value = response
                }.onFailure { throwable ->
                }
        }
    }
}
