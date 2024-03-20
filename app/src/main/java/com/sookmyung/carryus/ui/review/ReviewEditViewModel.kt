package com.sookmyung.carryus.ui.review

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
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

    private val _reviewDetailLiveData = MutableLiveData<ReviewDetail>()
    val reviewDetailLiveData: LiveData<ReviewDetail> = _reviewDetailLiveData

    private val _reservationListLiveData = MutableLiveData<ReservationList>()
    val reservationListLiveData: LiveData<ReservationList> = _reservationListLiveData

    init {
        _textCount.value = "0/1000"
    }

    companion object{
        private const val MAXIMUM_LENGTH = 1000
    }

    fun onTextChanged(s: CharSequence) {
        val count = s.length
        _textCount.value = "$count/$MAXIMUM_LENGTH"
    }

    fun initializeTextCount(reviewDetail: ReviewDetail) {
        val count = reviewDetail.reviewContent.length
        _textCount.value = "$count/$MAXIMUM_LENGTH"
    }

    fun setReviewDetail(reviewDetail: ReviewDetail) {
        _reviewDetailLiveData.value = reviewDetail
        initializeTextCount(reviewDetail)

    }

    fun setReservationList(reservationList: ReservationList) {
        _reservationListLiveData.value = reservationList
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
