package com.sookmyung.carryus.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.domain.entity.LocationStore
import com.sookmyung.carryus.domain.entity.Position
import com.sookmyung.carryus.domain.entity.StoreSearchResult
import com.sookmyung.carryus.domain.usecase.GetLocationStoreListUseCase
import com.sookmyung.carryus.domain.usecase.GetUserLocationStoreListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getUserLocationStoreList: GetUserLocationStoreListUseCase,
    private val getLocationStoreList: GetLocationStoreListUseCase
) : ViewModel() {
    private val _currentLocation: MutableLiveData<Position> = MutableLiveData(Position(0.0, 0.0))
    val currentLocation: LiveData<Position> get() = _currentLocation

    private val _selectedStoreId: MutableLiveData<Int> = MutableLiveData()
    val selectedStoreId: LiveData<Int> get() = _selectedStoreId

    private val _searchStoreList: MutableLiveData<List<StoreSearchResult>> = MutableLiveData()
    val searchStoreList: LiveData<List<StoreSearchResult>> get() = _searchStoreList

    private val _locationStoreList: MutableLiveData<List<LocationStore>> = MutableLiveData()
    val locationStoreList: LiveData<List<LocationStore>> get() = _locationStoreList

    // TODO 검색 반환 결과로 재검색 할게요 버튼 visible 처리
    private val _isSearchSuccess: MutableLiveData<Boolean> = MutableLiveData(false)
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

    private fun getLocationStore(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            getLocationStoreList(latitude, longitude)
                .onSuccess { response ->
                    _locationStoreList.value = response
                }.onFailure { throwable ->
                    Timber.e("서버 통신 실패 -> ${throwable.message}")
                }
        }
    }

    fun findCoordinatesByStoreId(storeId: Int) {
        _searchStoreList.value?.find { it.storeId == storeId }?.let { storeSearchResult ->
            getLocationStore(storeSearchResult.latitude, storeSearchResult.longitude)
        }
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
//                    _isSearchSuccess.value = true
                    _searchStoreList.value = response
                }.onFailure { throwable ->
                    _isSearchSuccess.value = false
                    Timber.e("서버 통신 실패 -> ${throwable.message}")
                }
        }
    }

    fun clickReloadBtn() {
        _locationStoreList.value = emptyList()
    }

    fun updateIsSearchSuccess(success: Boolean) {
        _isSearchSuccess.value = success
    }

    fun updateCurrentLocation(position: Position) {
        _currentLocation.value = position
    }

    fun updateSelectedStoreId(storeId: Int) {
        _selectedStoreId.value = storeId
    }
}
