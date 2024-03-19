package com.sookmyung.carryus.ui.search.storedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.StoreInfo
import com.sookmyung.carryus.domain.entity.StoreReview

class StoreDetailViewModel : ViewModel() {
    private val _reviewList: MutableLiveData<List<StoreReview>> = MutableLiveData(listOf())
    val reviewList: LiveData<List<StoreReview>> get() = _reviewList

    private val _storeInfo: MutableLiveData<StoreInfo> = MutableLiveData(null)
    val storeInfo: LiveData<StoreInfo> get() = _storeInfo

    init {
        getStoreInfo()
    }

    private fun getStoreInfo() {
        _storeInfo.value = StoreInfo(
            1,
            "건빵가게",
            "서울특별시 강남구 어쩌구",
            "월,화,수",
            "목-일 11:00 - 12:00",
            "02-234-2343",
            5000,
            6000,
            7000,
            8000,
            5,
            3,
            1,
            0,
            "3.5"
        )

        _reviewList.value = listOf(

            StoreReview("으니", "2023.12.31", "굿", "4.0"),
            StoreReview(
                "강희",
                "2022.12.31",
                "굿이에요.그래도 두 줄은 넘겨보고 싶어서 작성하고 있어요. 두 줄이 언제 넘어갈까요?",
                "4.0"
            ),
            StoreReview("영철", "2025.10.3", "굿", "4.0"),
            StoreReview("이름", "2022.12.31", "굿", "4.0"),
            StoreReview("나리", "2026.11.31", "굿", "4.0"),
            StoreReview("현진", "2023.2.5", "굿", "4.0"),
            StoreReview("그만", "2023.6.26", "굿", "4.0")
        )
    }
}
