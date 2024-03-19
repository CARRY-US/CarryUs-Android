package com.sookmyung.carryus.ui.search.storedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.BaggageTypeInfo
import com.sookmyung.carryus.domain.entity.StoreDetail
import com.sookmyung.carryus.domain.entity.StoreDetailReview
import com.sookmyung.carryus.domain.entity.StoreReview

class StoreDetailViewModel : ViewModel() {
    private val _storeDetailReview: MutableLiveData<StoreDetailReview> = MutableLiveData()
    val storeDetailReview: LiveData<StoreDetailReview> get() = _storeDetailReview

    private val _storeInfo: MutableLiveData<StoreDetail> = MutableLiveData(null)
    val storeInfo: LiveData<StoreDetail> get() = _storeInfo

    init {
        getStoreInfo()
    }

    private fun getStoreInfo() {
        _storeInfo.value = StoreDetail(
            1,
            "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.tripadvisor.co.kr%2FLocationPhotoDirectLink-g294197-d6115140-i89651503-Sarangchae-Seoul.html&psig=AOvVaw1seb0YPuW-R_cQhQYAIcKG&ust=1710902940307000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCKDtnqKo_4QDFQAAAAAdAAAAABAH",
            "건빵가게",
            "서울특별시 강남구 어쩌구",
            "월,화 휴무",
            "수 - 금 12:00 ~ 19:00",
            "02-234-2343",
            listOf(
                BaggageTypeInfo("20인치 미만", 3, 50000),
                BaggageTypeInfo("20인치", 2, 60000),
                BaggageTypeInfo("24인치", 1, 70000),
                BaggageTypeInfo("28인치 이상", 0, 80000)
            )
        )

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
}
