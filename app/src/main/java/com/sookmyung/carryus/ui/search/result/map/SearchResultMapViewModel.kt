package com.sookmyung.carryus.ui.search.result.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.domain.entity.LocationStore
import com.sookmyung.carryus.domain.entity.Position
import com.sookmyung.carryus.domain.entity.StoreSearchResult
import com.sookmyung.carryus.domain.usecase.GetLocationStoreListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchResultMapViewModel @Inject constructor(
    private val getLocationStoreList: GetLocationStoreListUseCase
) : ViewModel() {
    private val _searchResultList: MutableLiveData<List<StoreSearchResult>> =
        MutableLiveData()
    val searchResultList: LiveData<List<StoreSearchResult>> get() = _searchResultList

    private val _selectedStoreId: MutableLiveData<Int> = MutableLiveData()
    val selectedStoreId: LiveData<Int> get() = _selectedStoreId

    private val _middleLocation: MutableLiveData<Position> = MutableLiveData(Position(0.0, 0.0))
    val middleLocation: LiveData<Position> get() = _middleLocation

    private val _locationStoreList: MutableLiveData<List<LocationStore>> = MutableLiveData()
    val locationStoreList: LiveData<List<LocationStore>> get() = _locationStoreList

    fun updateMiddleLocation(position: Position) {
        _middleLocation.value = position
    }

    fun updateSelectedStoreId(storeId: Int) {
        _selectedStoreId.value = storeId
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
        _searchResultList.value?.find { it.storeId == storeId }?.let { list ->
            getLocationStore(list.latitude, list.longitude)
        }
    }

    fun updateSearchStoreList(storeList: List<StoreSearchResult>) {
        _searchResultList.value = storeList
    }
}
