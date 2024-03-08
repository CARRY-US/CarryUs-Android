package com.sookmyung.carryus.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.Position

class SearchViewModel : ViewModel() {
    private val _storeList: MutableLiveData<List<Position>> =
        MutableLiveData(listOf(Position(33.450701, 126.570667), Position(37.542360089442, 126.96121708264)))
    val storeList: LiveData<List<Position>> get() = _storeList

    private val _currentLocation: MutableLiveData<Position> = MutableLiveData(Position(0.0, 0.0))
    val currentLocation: LiveData<Position> get() = _currentLocation

    fun updateCurrentLocation(position: Position) {
        _currentLocation.value = position
    }
}
