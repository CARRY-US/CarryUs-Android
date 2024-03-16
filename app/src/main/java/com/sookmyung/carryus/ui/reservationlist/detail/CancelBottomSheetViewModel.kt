package com.sookmyung.carryus.ui.reservationlist.detail

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CancelBottomSheetViewModel : ViewModel() {
    private val _textCount = MutableLiveData<String>()
    val textCount: LiveData<String> = _textCount

    init {
        _textCount.value = "0/1000"
    }

    fun onTextChanged(s: CharSequence) {
        val count = s.length
        _textCount.value = "$count/1000"
    }
}

@BindingAdapter("app:textCount")
fun setTextCount(textView: TextView, count: String) {
    textView.text = count
}

@BindingAdapter("app:textWatcher")
fun bindTextWatcher(editText: EditText, viewModel: CancelBottomSheetViewModel?) {
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
