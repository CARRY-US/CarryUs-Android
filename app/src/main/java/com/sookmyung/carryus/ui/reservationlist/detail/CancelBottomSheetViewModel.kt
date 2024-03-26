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
import com.sookmyung.carryus.domain.usecase.reservation.PostCancelReservationUseCase
import com.sookmyung.carryus.util.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CancelBottomSheetViewModel @Inject constructor(
    private val postCancelReservationUseCase: PostCancelReservationUseCase
) : ViewModel() {
    val cancelResultLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val cancelReason = MutableLiveData<String>()

    fun setCancelReason(newText: String) {
        cancelReason.value = newText
    }

    fun postCancelReservation(cancelReservationRequest: CancelReservationRequest) {
        viewModelScope.launch {
            postCancelReservationUseCase(cancelReservationRequest)
                .onSuccess { response ->
                    cancelResultLiveData.postValue(true)
                }.onFailure { throwable ->
                    cancelResultLiveData.postValue(false)
                }
        }
    }
}
