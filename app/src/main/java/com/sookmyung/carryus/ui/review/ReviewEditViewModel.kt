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
import com.sookmyung.carryus.domain.entity.ReservationList
import com.sookmyung.carryus.domain.entity.ReviewDetail

class ReviewEditViewModel : ViewModel(){
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

    init {
        _textCount.value = "0/1000"
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

    fun initializeDataSet(reviewDetail: ReviewDetail) {
        val count = reviewDetail.reviewContent.length
        _textCount.value = "$count/$MAXIMUM_LENGTH"
        _rating.value = reviewDetail.reviewRating
        _reviewContent.value = reviewDetail.reviewContent
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
        Log.d("ReviewEditViewModel", "reviewContent: ${_reviewContent.value}")
        closeActivityEvent.value = Unit
    }
}

@BindingAdapter("app:textCount")
fun setTextCount(textView: TextView, count: String) {
    textView.text = count
}

@BindingAdapter("app:textWatcher")
fun bindTextWatcher(editText: EditText, viewModel: ReviewEditViewModel?) {
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
fun setRatingChangeListener(ratingBar: RatingBar, viewModel: ReviewEditViewModel?) {
    viewModel?.let {
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.onRatingChanged(rating)
        }
    }
}