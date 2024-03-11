package com.sookmyung.carryus.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.Position
import com.sookmyung.carryus.domain.entity.SimpleStoreReviewInfo

class SearchViewModel : ViewModel() {
    private val _storeList: MutableLiveData<List<Position>> =
        MutableLiveData(
            listOf(
                Position(33.450701, 126.570667),
                Position(37.542360089442, 126.96121708264)
            )
        )
    val storeList: LiveData<List<Position>> get() = _storeList

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
}
