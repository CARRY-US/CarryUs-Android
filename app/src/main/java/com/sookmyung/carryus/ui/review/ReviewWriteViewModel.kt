package com.sookmyung.carryus.ui.review

import android.util.Log
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.data.entitiy.request.ReviewRequest
import com.sookmyung.carryus.domain.entity.ReservationList
import com.sookmyung.carryus.domain.usecase.reservation.PostReviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewWriteViewModel @Inject constructor(
    private val postReviewUseCase: PostReviewUseCase
): ViewModel(){
    private val _rating = MutableLiveData<Float>()
    val rating: LiveData<Float> = _rating

    val reviewContent = MutableLiveData<String>()

    private val _reservationListLiveData = MutableLiveData<ReservationList>()
    val reservationListLiveData: LiveData<ReservationList> = _reservationListLiveData

    val closeActivityEvent = MutableLiveData<Unit>()

    val isSaveEnabled: LiveData<Boolean> = rating.map { rating ->
        rating > 0
    }

    init {
        _rating.value = 0f
    }

    fun setReviewContent(newText: String) {
        reviewContent.value = newText
    }

    fun onRatingChanged(rating: Float) {
        _rating.value = rating
        Log.d("ReviewEditViewModel", "rating: $rating")
    }

    fun setReservationList(reservationInfo: ReservationList) {
        _reservationListLiveData.value = reservationInfo
    }

    fun postReview(reservationId: Int, reviewRequest: ReviewRequest) {
        viewModelScope.launch {
            postReviewUseCase(reservationId, reviewRequest )
                .onSuccess { response ->
                    Log.d("ReviewWriteViewModel", "리뷰 작성 성공 -> ${response}")
                    closeActivityEvent.postValue(Unit)
                }.onFailure { throwable ->
                    Log.e("ReviewWriteViewModel", "리뷰 작성 실패 -> ${throwable.message}")
                }
        }

    }
}

@BindingAdapter("app:ratingChangeListener")
fun setRatingChangeListener(ratingBar: RatingBar, viewModel: ReviewWriteViewModel?) {
    viewModel?.let {
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.onRatingChanged(rating)
        }
    }
}
