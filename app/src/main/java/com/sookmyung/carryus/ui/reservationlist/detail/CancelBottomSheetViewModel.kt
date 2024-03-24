package com.sookmyung.carryus.ui.reservationlist.detail

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.data.entitiy.request.CancelReservationRequest
import com.sookmyung.carryus.domain.usecase.reservation.postCancelReservationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CancelBottomSheetViewModel @Inject constructor(
    private val postCancelReservationUseCase: postCancelReservationUseCase
) : ViewModel() {
    private val _textCount = MutableLiveData<String>()
    val textCount: LiveData<String> = _textCount

    private val _isTextEmpty = MutableLiveData<Boolean>()
    val isTextEmpty: LiveData<Boolean> = _isTextEmpty

    val cancelResultLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val isCancelBtnEnabled: LiveData<Boolean> = _isTextEmpty.map { isTextEmpty ->
        isTextEmpty == false
    }

    init {
        _textCount.value = "0/1000"
        _isTextEmpty.value = true
    }

    companion object{
        private const val MAXIMUM_LENGTH = 1000
    }

    fun onTextChanged(s: CharSequence) {
        val count = s.length
        _textCount.value = "$count/$MAXIMUM_LENGTH"
        _isTextEmpty.value = s.isEmpty()
    }


    fun postCancelReservation(cancelReservationRequest: CancelReservationRequest) {
        viewModelScope.launch {
            postCancelReservationUseCase(cancelReservationRequest)
                .onSuccess { response ->
                    Log.d("ReservationDetailViewModel", "취소 성공 -> ${response}")
                    cancelResultLiveData.postValue(true)
                }.onFailure { throwable ->
                    Log.e("ReservationDetailViewModel", "취소 실패 -> ${throwable.message}")
                    cancelResultLiveData.postValue(false)
                }
        }
    }
}

@BindingAdapter("app:reasonTextCount")
fun setReasonTextCount(textView: TextView, count: String) {
    textView.text = count
}

@BindingAdapter("app:reasonTextWatcher")
fun bindReasonTextWatcher(editText: EditText, viewModel: CancelBottomSheetViewModel?) {
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
