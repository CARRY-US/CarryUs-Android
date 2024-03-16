package com.sookmyung.carryus.ui.search.result

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.Position
import com.sookmyung.carryus.domain.entity.SimpleStoreReviewInfo

class SearchResultViewModel : ViewModel() {
    private val _searchResultList: MutableLiveData<List<SimpleStoreReviewInfo>> = MutableLiveData(
        emptyList()
//        listOf(n m
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

    private val _storePositionList: MutableLiveData<List<Position>> =
        MutableLiveData(
            listOf(
                Position(33.450701, 126.570667),
                Position(37.542360089442, 126.96121708264)
            )
        )
    val storePositionList: LiveData<List<Position>> get() = _storePositionList

    private val _selectedStoreId: MutableLiveData<Int> = MutableLiveData()
    val selectedStoreItd: LiveData<Int> get() = _selectedStoreId

    private val _startSearch: MutableLiveData<Boolean> = MutableLiveData(false)
    val startSearch: LiveData<Boolean> get() = _startSearch

    private val _currentLocation: MutableLiveData<Position> = MutableLiveData(Position(0.0, 0.0))
    val currentLocation: LiveData<Position> get() = _currentLocation

    private val _simpleStoreReviewInfoList: MutableLiveData<List<SimpleStoreReviewInfo>> =
        MutableLiveData(
            listOf(
                SimpleStoreReviewInfo(
                    "test1",
                    "first store",
                    "https://www.jejudreamtower.com/assets/global/jdt/m/images/etc/dine_cafe8_kv3_mo.jpg",
                    "4.5",
                    "99+"
                ),
                SimpleStoreReviewInfo(
                    "test2",
                    "second store",
                    "https://www.jejudreamtower.com/assets/global/jdt/m/images/etc/dine_cafe8_kv3_mo.jpg",
                    "2.5",
                    "30"
                )
            )
        )
    val simpleStoreReviewInfoList: LiveData<List<SimpleStoreReviewInfo>> get() = _simpleStoreReviewInfoList

    fun updateCurrentLocation(position: Position) {
        _currentLocation.value = position
    }

    fun updateSelectedStoreId(storeId: String) {
//        _selectedStoreId.value = storeId
        Log.e("kang", "storeId $storeId")
    }

    fun getResult() {
        _startSearch.value = true
    }
}