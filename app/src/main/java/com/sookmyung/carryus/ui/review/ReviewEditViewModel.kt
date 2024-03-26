package com.sookmyung.carryus.ui.review

import android.util.Log
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.data.entitiy.request.ReviewRequest
import com.sookmyung.carryus.domain.entity.ReviewDetail
import com.sookmyung.carryus.domain.entity.ReviewStoreInfo
import com.sookmyung.carryus.domain.usecase.review.GetReviewDetailUseCase
import com.sookmyung.carryus.domain.usecase.review.GetReviewStoreInfoUseCase
import com.sookmyung.carryus.domain.usecase.review.PatchReviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewEditViewModel @Inject constructor(
    val getReviewDetailUseCase: GetReviewDetailUseCase,
    val getReviewStoreInfoUseCase: GetReviewStoreInfoUseCase,
    val patchReviewUseCase: PatchReviewUseCase
) : ViewModel(){
    private val _rating = MutableLiveData<Float>()
    val rating: LiveData<Float> = _rating

    val reviewContent = MutableLiveData<String>()

    private val _reviewDetailLiveData = MutableLiveData<ReviewDetail>()
    val reviewDetailLiveData: LiveData<ReviewDetail> = _reviewDetailLiveData

    private val _reviewStoreInfoLiveData = MutableLiveData<ReviewStoreInfo>()
    val reviewStoreInfoLiveData: LiveData<ReviewStoreInfo> = _reviewStoreInfoLiveData

    val updateResultLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun setReviewContent(newText: String) {
        reviewContent.value = newText
    }

    fun onRatingChanged(rating: Float) {
        _rating.value = rating
    }

    fun initializeDataSet(reviewDetail: ReviewDetail) {
        _rating.value = reviewDetail.reviewRating.toFloat()
        reviewContent.value = reviewDetail.reviewContent
    }

    fun setReviewDetail(reviewId: Int) {
        viewModelScope.launch {
            getReviewDetailUseCase(reviewId)
                .onSuccess { response ->
                    _reviewDetailLiveData.value = response
                    initializeDataSet(response)
                }.onFailure { throwable ->
                    _reviewDetailLiveData.value = ReviewDetail()
                }
        }
    }

    fun setReservationList(reviewId: Int) {
        viewModelScope.launch {
            getReviewStoreInfoUseCase(reviewId)
                .onSuccess { response ->
                    _reviewStoreInfoLiveData.value = response
                }.onFailure { throwable ->
                    _reviewStoreInfoLiveData.value = ReviewStoreInfo()
                }
        }
    }

    fun updateReview(reviewId: Int) {
        viewModelScope.launch {
            rating.value?.let {
                ReviewRequest(
                    reviewRating = it.toDouble(),
                    reviewContent = reviewContent.value ?: ""
                )
            }?.let {
                patchReviewUseCase(reviewId, it)
                    .onSuccess { response ->
                        updateResultLiveData.postValue(true)
                    }.onFailure { response ->
                        updateResultLiveData.postValue(false)
                    }
            }
        }
    }
}

@BindingAdapter("app:ratingChangeListener")
fun setRatingChangeListener(ratingBar: RatingBar, viewModel: ReviewEditViewModel?) {
    viewModel?.let {
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.onRatingChanged(rating)
        }
    }
}
