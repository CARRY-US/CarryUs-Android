package com.sookmyung.carryus.ui.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.data.source.LocalDataSource
import com.sookmyung.carryus.domain.entity.MyProfile
import com.sookmyung.carryus.domain.entity.MyReviews
import com.sookmyung.carryus.domain.repository.AuthRepository
import com.sookmyung.carryus.domain.usecase.my.GetMyProfileUseCase
import com.sookmyung.carryus.domain.usecase.my.GetMyReviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getMyProfileUseCase: GetMyProfileUseCase,
    private val getMyReviewsUseCase: GetMyReviewsUseCase,
    private val localDataSource: LocalDataSource,
    private val authRepository: AuthRepository
): ViewModel(){
    private val _navigateToDetail = MutableLiveData<MyReviews>()

    private val _myProfile = MutableLiveData<MyProfile>()
    val myProfile: LiveData<MyProfile>
        get() = _myProfile

    private val _myReviews = MutableLiveData<List<MyReviews>>()
    val myReviews: LiveData<List<MyReviews>>
        get() = _myReviews

    val showDialog = MutableLiveData<Boolean>()

    val navigateToDetail: LiveData<MyReviews>
        get() = _navigateToDetail

    private val _isLogout = MutableLiveData<Boolean>()
    val isLogout: LiveData<Boolean>
        get() = _isLogout

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
                    Timber.e("서버 통신 실패 -> ${throwable.message}")
                }
        }
    }

    fun getMyReviews() {
        Log.d("MyPageViewModel", "getMyReviews")
        viewModelScope.launch {
            getMyReviewsUseCase()
                .onSuccess { response ->
                    _myReviews.value = response
                }.onFailure { throwable ->
                    Timber.e("서버 통신 실패 -> ${throwable.message}")
                }
        }
    }

    fun logout() {
        localDataSource.isUserSignIn = false
        authRepository.resetAccessToken()
    }
}
