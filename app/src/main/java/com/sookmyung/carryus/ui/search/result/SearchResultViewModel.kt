package com.sookmyung.carryus.ui.search.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sookmyung.carryus.domain.entity.StoreSearchResult
import com.sookmyung.carryus.domain.usecase.GetCityBaseStoreListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    val getCityBaseStoreListUseCase: GetCityBaseStoreListUseCase
) : ViewModel() {
    private val _searchResultList: MutableLiveData<List<StoreSearchResult>> =
        MutableLiveData()
    val searchResultList: LiveData<List<StoreSearchResult>> get() = _searchResultList

    private val _selectedStoreId: MutableLiveData<Int> = MutableLiveData()
    val selectedStoreId: LiveData<Int> get() = _selectedStoreId

    private val _startSearch: MutableLiveData<Boolean> = MutableLiveData(false)
    val startSearch: LiveData<Boolean> get() = _startSearch
    val word: MutableLiveData<String> = MutableLiveData("")

    fun searchCityBase() {
        viewModelScope.launch {
            getCityBaseStoreListUseCase(word.value ?: "")
                .onSuccess { response ->
                    _searchResultList.value = response
                    _startSearch.value = true
                }
                .onFailure { throwable ->
                    _startSearch.value = false
                    Timber.e("서버 통신 실패 -> ${throwable.message}")
                }
        }
    }

    fun updateSelectedStoreId(storeId: Int) {
        _selectedStoreId.value = storeId
    }
}
