package com.sookmyung.carryus.ui.search.result

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.SimpleStoreReviewInfo

class SearchResultViewModel : ViewModel() {
    private val _searchResultList: MutableLiveData<List<SimpleStoreReviewInfo>> = MutableLiveData(
        emptyList()
//        listOf(
//            SimpleStoreReviewInfo(
//                "test1",
//                "first store",
//                "https://www.jejudreamtower.com/assets/global/jdt/m/images/etc/dine_cafe8_kv3_mo.jpg",
//                "4.5",
//                "99+"
//            ),
//            SimpleStoreReviewInfo(
//                "test4",
//                "second store",
//                "https://www.jejudreamtower.com/assets/global/jdt/m/images/etc/dine_cafe8_kv3_mo.jpg",
//                "2.5",
//                "30"
//            ),
//            SimpleStoreReviewInfo(
//                "test5",
//                "first store",
//                "https://www.jejudreamtower.com/assets/global/jdt/m/images/etc/dine_cafe8_kv3_mo.jpg",
//                "4.5",
//                "99+"
//            ),
//            SimpleStoreReviewInfo(
//                "test6",
//                "second store",
//                "https://www.jejudreamtower.com/assets/global/jdt/m/images/etc/dine_cafe8_kv3_mo.jpg",
//                "2.5",
//                "30"
//            ),
//            SimpleStoreReviewInfo(
//                "test7",
//                "first store",
//                "https://www.jejudreamtower.com/assets/global/jdt/m/images/etc/dine_cafe8_kv3_mo.jpg",
//                "4.5",
//                "99+"
//            ),
//            SimpleStoreReviewInfo(
//                "test8",
//                "second store",
//                "https://www.jejudreamtower.com/assets/global/jdt/m/images/etc/dine_cafe8_kv3_mo.jpg",
//                "2.5",
//                "30"
//            )
//        )
    )
    val searchResultList: LiveData<List<SimpleStoreReviewInfo>> get() = _searchResultList

    private val _selectedStoreId: MutableLiveData<Int> = MutableLiveData()
    val selectedStoreItd: LiveData<Int> get() = _selectedStoreId

    private val _startSearch: MutableLiveData<Boolean> = MutableLiveData(false)
    val startSearch: LiveData<Boolean> get() = _startSearch

    fun updateSelectedStoreId(storeId: String) {
//        _selectedStoreId.value = storeId
        Log.e("kang", "storeId $storeId")
    }

    fun getResult() {
        _startSearch.value = true
    }
}
