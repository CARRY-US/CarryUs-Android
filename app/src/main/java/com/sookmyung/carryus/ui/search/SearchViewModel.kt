package com.sookmyung.carryus.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.domain.entity.Position
import com.sookmyung.carryus.domain.entity.StoreSearchResult
import com.sookmyung.carryus.domain.usecase.GetUserLocationStoreList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getUserLocationStoreList: GetUserLocationStoreList
) : ViewModel() {
    private val _currentLocation: MutableLiveData<Position> = MutableLiveData(Position(0.0, 0.0))
    val currentLocation: LiveData<Position> get() = _currentLocation

    private val _selectedStoreId: MutableLiveData<Int> = MutableLiveData()
    val selectedStoreId: LiveData<Int> get() = _selectedStoreId

    private val _searchStoreList: MutableLiveData<List<StoreSearchResult>> = MutableLiveData()
    val searchStoreList: LiveData<List<StoreSearchResult>> get() = _searchStoreList

    // TODO 검색 반환 결과로 재검색 할게요 버튼 visible 처리
    private val _isSearchSuccess: MutableLiveData<Boolean> = MutableLiveData(true)
    val isSearchSuccess: LiveData<Boolean> get() = _isSearchSuccess
    private val minX: MutableLiveData<Double> = MutableLiveData()
    private val maxX: MutableLiveData<Double> = MutableLiveData()
    private val minY: MutableLiveData<Double> = MutableLiveData()
    private val maxY: MutableLiveData<Double> = MutableLiveData()
    fun updateCurrentPosition(newMinx: Double, newMaxX: Double, newMinY: Double, newMaxY: Double) {
        minX.value = newMinx
        maxX.value = newMaxX
        minY.value = newMinY
        maxY.value = newMaxY
        updateUserLocationStoreList()
    }

    private fun updateUserLocationStoreList() {
        viewModelScope.launch {
            getUserLocationStoreList(
                minX.value ?: 0.0,
                maxX.value ?: 0.0,
                minY.value ?: 0.0,
                maxY.value
                    ?: 0.0
            )
                .onSuccess { response ->
                    _isSearchSuccess.value = true
                    _searchStoreList.value = response
                }.onFailure { throwable ->
                    _isSearchSuccess.value = false
                    Timber.e("서버 통신 실패 -> ${throwable.message}")
                }
        }
    }

    fun updateCurrentLocation(position: Position) {
        _currentLocation.value = position
    }

    fun updateSelectedStoreId(storeId: Int) {
        _selectedStoreId.value = storeId
    }
}
