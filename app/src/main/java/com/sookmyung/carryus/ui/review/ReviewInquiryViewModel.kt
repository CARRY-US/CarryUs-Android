package com.sookmyung.carryus.ui.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.domain.entity.ReviewDetail
import com.sookmyung.carryus.domain.entity.ReviewStoreInfo
import com.sookmyung.carryus.domain.usecase.review.GetReviewDetailUseCase
import com.sookmyung.carryus.domain.usecase.review.GetReviewStoreInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewInquiryViewModel @Inject constructor(
    val getReviewDetailUseCase: GetReviewDetailUseCase,
    val getReviewStoreInfoUseCase: GetReviewStoreInfoUseCase
): ViewModel() {
    private val _reviewDetailLiveData = MutableLiveData<ReviewDetail>()
    val reviewDetailLiveData: LiveData<ReviewDetail> = _reviewDetailLiveData

    private val _reviewStoreInfoLiveData = MutableLiveData<ReviewStoreInfo>()
    val reviewStoreInfoLiveData: LiveData<ReviewStoreInfo> = _reviewStoreInfoLiveData

    fun setReviewDetail(reviewId: Int) {
        viewModelScope.launch {
            getReviewDetailUseCase(reviewId)
                .onSuccess { response ->
                    _reviewDetailLiveData.value = response
                }.onFailure { throwable ->
                    throwable.printStackTrace()
                }
        }
    }

    fun setReservationList(reviewId: Int) {
        viewModelScope.launch {
            getReviewStoreInfoUseCase(reviewId)
                .onSuccess { response ->
                    _reviewStoreInfoLiveData.value = response
                }.onFailure { throwable ->
                    throwable.printStackTrace()
                }
        }
    }
}
