package com.sookmyung.carryus.ui.review

import android.util.Log
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.ReservationList
import com.sookmyung.carryus.domain.entity.ReviewDetail

class ReviewEditViewModel : ViewModel(){
    private val _rating = MutableLiveData<Float>()
    val rating: LiveData<Float> = _rating

    val reviewContent = MutableLiveData<String>()

    private val _reviewDetailLiveData = MutableLiveData<ReviewDetail>()
    val reviewDetailLiveData: LiveData<ReviewDetail> = _reviewDetailLiveData

    private val _reservationListLiveData = MutableLiveData<ReservationList>()
    val reservationListLiveData: LiveData<ReservationList> = _reservationListLiveData

    val closeActivityEvent = MutableLiveData<Unit>()

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

    fun setReviewDetail(reviewDetail: ReviewDetail) {
        _reviewDetailLiveData.value = reviewDetail
        initializeDataSet(reviewDetail)
    }

    fun setReservationList(reservationList: ReservationList) {
        _reservationListLiveData.value = reservationList
    }

    fun requestCloseActivity() {
        Log.d("ReviewEditViewModel", "onSaveButtonClick")
        Log.d("ReviewEditViewModel", "rating: ${_rating.value}")
        Log.d("ReviewEditViewModel", "reviewContent: ${reviewContent.value}")
        closeActivityEvent.value = Unit
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
