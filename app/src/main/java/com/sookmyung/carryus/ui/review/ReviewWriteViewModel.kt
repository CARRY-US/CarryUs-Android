package com.sookmyung.carryus.ui.review

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.data.entitiy.request.ReviewRequest
import com.sookmyung.carryus.domain.entity.ReservationList
import com.sookmyung.carryus.domain.entity.ReviewDetail
import com.sookmyung.carryus.domain.usecase.reservation.PostReviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewWriteViewModel @Inject constructor(
    private val postReviewUseCase: PostReviewUseCase
): ViewModel(){
    private val _textCount = MutableLiveData<String>()
    val textCount: LiveData<String> = _textCount

    private val _rating = MutableLiveData<Float>()
    val rating: LiveData<Float> = _rating

    private val _reviewContent = MutableLiveData<String>()
    val reviewContent: LiveData<String> = _reviewContent

    private val _reviewDetailLiveData = MutableLiveData<ReviewDetail>()
    val reviewDetailLiveData: LiveData<ReviewDetail> = _reviewDetailLiveData

    private val _reservationListLiveData = MutableLiveData<ReservationList>()
    val reservationListLiveData: LiveData<ReservationList> = _reservationListLiveData

    val closeActivityEvent = MutableLiveData<Unit>()

    val isSaveEnabled: LiveData<Boolean> = rating.map { rating ->
        rating > 0
    }

    init {
        _textCount.value = "0/1000"
        _rating.value = 0f
        _reviewContent.value = ""
    }

    companion object{
        private const val MAXIMUM_LENGTH = 1000
    }

    fun onTextChanged(s: CharSequence) {
        val count = s.length
        _reviewContent.value = s.toString()
        _textCount.value = "$count/$MAXIMUM_LENGTH"
    }

    fun onRatingChanged(rating: Float) {
        _rating.value = rating
        Log.d("ReviewEditViewModel", "rating: $rating")
    }

    fun initializeDataSet() {
        val count = 0
        _textCount.value = "$count/$MAXIMUM_LENGTH"
    }

    fun setReservationList(reservationList: ReservationList) {
        _reservationListLiveData.value = reservationList
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

@BindingAdapter("app:reviewWriteTextCount")
fun setReviewWriteTextCount(textView: TextView, count: String) {
    textView.text = count
}

@BindingAdapter("app:reviewWriteTextWatcher")
fun bindReviewWriteTextWatcher(editText: EditText, viewModel: ReviewWriteViewModel?) {
    viewModel?.let {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onTextChanged(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
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
