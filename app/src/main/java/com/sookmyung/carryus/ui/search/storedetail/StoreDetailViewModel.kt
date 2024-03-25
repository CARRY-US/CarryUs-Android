package com.sookmyung.carryus.ui.search.storedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.domain.entity.StoreDetail
import com.sookmyung.carryus.domain.entity.StoreDetailReview
import com.sookmyung.carryus.domain.entity.StoreReview
import com.sookmyung.carryus.domain.usecase.GetStoreDetailInfoUseCase
import com.sookmyung.carryus.domain.usecase.GetStoreDetailReviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StoreDetailViewModel @Inject constructor(
    val getStoreDetailInfoUseCase: GetStoreDetailInfoUseCase,
    val getStoreDetailReviewUseCase: GetStoreDetailReviewUseCase
) : ViewModel() {
    private val _storeId: MutableLiveData<Int> = MutableLiveData()
    val storeId: LiveData<Int> get() = _storeId
    private val _storeDetailReview: MutableLiveData<StoreDetailReview> = MutableLiveData()
    val storeDetailReview: LiveData<StoreDetailReview> get() = _storeDetailReview

    private val _storeInfo: MutableLiveData<StoreDetail> = MutableLiveData(null)
    val storeInfo: LiveData<StoreDetail> get() = _storeInfo

    private fun getStoreInfo() {
        viewModelScope.launch {
            getStoreDetailInfoUseCase(storeId.value ?: 0)
                .onSuccess { response ->
                    _storeInfo.value = response
                }
                .onFailure { throwable ->
                    Timber.e("서버 통신 실패 -> ${throwable.message}")
                }
        }
    }

    private fun getStoreReview() {
        viewModelScope.launch {
            getStoreDetailReviewUseCase(storeId.value ?: 0)
                .onSuccess { response ->
                    _storeDetailReview.value = response
                }
                .onFailure { throwable ->
                    Timber.e("서버 통신 실패 -> ${throwable.message}")
                }
        }
    }

    fun updateStoreId(storeId: Int) {
        _storeId.value = storeId
        getStoreInfo()
        getStoreReview()
    }
}
