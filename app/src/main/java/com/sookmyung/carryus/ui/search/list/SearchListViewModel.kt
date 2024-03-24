package com.sookmyung.carryus.ui.search.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.StoreSearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor() : ViewModel() {

    private val _searchStoreList: MutableLiveData<List<StoreSearchResult>> = MutableLiveData()
    val searchStoreList: LiveData<List<StoreSearchResult>> get() = _searchStoreList

    private val _selectedStoreId: MutableLiveData<Int> = MutableLiveData()
    val selectedStoreId: LiveData<Int> get() = _selectedStoreId

    fun updateSearchStoreList(storeList: List<StoreSearchResult>) {
        _searchStoreList.value = storeList
    }

    fun updateSelectedStoreId(storeId: Int) {
        _selectedStoreId.value = storeId
    }
}
