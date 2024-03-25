package com.sookmyung.carryus.ui.search.storedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.domain.entity.BaggageTypeInfo
import com.sookmyung.carryus.domain.entity.StoreDetail
import com.sookmyung.carryus.domain.entity.StoreDetailReview
import com.sookmyung.carryus.domain.entity.StoreReview
import com.sookmyung.carryus.domain.usecase.GetStoreDetailInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StoreDetailViewModel @Inject constructor(
    val getStoreDetailInfoUseCase: GetStoreDetailInfoUseCase
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

        _storeDetailReview.value = StoreDetailReview(
            4.5, listOf(

                StoreReview(1, "으니", "2023.12.31", 4.0, "굿"),
                StoreReview(
                    2, "강희",
                    "2022.12.31",
                    4.0,
                    "굿이에요.그래도 두 줄은 넘겨보고 싶어서 작성하고 있어요. 두 줄이 언제 넘어갈까요?"
                ),
                StoreReview(3, "영철", "2025.10.3", 4.0, "굿"),
                StoreReview(4, "이름", "2022.12.31", 3.0, "굿"),
                StoreReview(5, "나리", "2026.11.31", 2.5, "굿"),
                StoreReview(6, "현진", "2023.2.5", 3.0, "굿"),
                StoreReview(7, "그만", "2023.6.26", 5.0, "굿")
            )
        )
    }

    fun updateStoreId(storeId: Int) {
        _storeId.value = storeId
        getStoreInfo()
    }
}
