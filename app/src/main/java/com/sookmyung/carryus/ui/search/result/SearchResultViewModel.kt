package com.sookmyung.carryus.ui.search.result

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sookmyung.carryus.domain.entity.Position
import com.sookmyung.carryus.domain.entity.StoreSearchResult

class SearchResultViewModel : ViewModel() {
    private val _searchResultList: MutableLiveData<List<StoreSearchResult>> = MutableLiveData(
        listOf(
            StoreSearchResult(
                1,
                "https://www.jejudreamtower.com/assets/global/jdt/m/images/etc/dine_cafe8_kv3_mo.jpg",
                "test1",
                "위치입니다.",
                "99+",
                "4.5",
                33.450701,
                126.570667
            ),
            StoreSearchResult(
                2,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.tripadvisor.co.kr%2FLocationPhotoDirectLink-g297390-d9861395-i294605803-Fresh_Fruit_Factory-Siem_Reap_Siem_Reap_Province.html&psig=AOvVaw1seb0YPuW-R_cQhQYAIcKG&ust=1710902940307000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCKDtnqKo_4QDFQAAAAAdAAAAABAQ",
                "test2",
                "위치 최대 18글자까지 노출된다는데 확인하게 길게 작성해보겠습니다.",
                "20",
                "4.5",
                33.450701,
                126.570667
            ),
            StoreSearchResult(
                3,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.tripadvisor.co.kr%2FLocationPhotoDirectLink-g297390-d9861395-i294605803-Fresh_Fruit_Factory-Siem_Reap_Siem_Reap_Province.html&psig=AOvVaw1seb0YPuW-R_cQhQYAIcKG&ust=1710902940307000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCKDtnqKo_4QDFQAAAAAdAAAAABAQ",
                "test3",
                "위치",
                "50",
                "2.0",
                30.450701,
                150.570667
            ),
            StoreSearchResult(
                4,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.tripadvisor.co.kr%2FLocationPhotoDirectLink-g297390-d9861395-i294605803-Fresh_Fruit_Factory-Siem_Reap_Siem_Reap_Province.html&psig=AOvVaw1seb0YPuW-R_cQhQYAIcKG&ust=1710902940307000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCKDtnqKo_4QDFQAAAAAdAAAAABAQ",
                "test4",
                "위치",
                "50",
                "2.0",
                30.450701,
                150.570667
            ),
            StoreSearchResult(
                5,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.tripadvisor.co.kr%2FLocationPhotoDirectLink-g297390-d9861395-i294605803-Fresh_Fruit_Factory-Siem_Reap_Siem_Reap_Province.html&psig=AOvVaw1seb0YPuW-R_cQhQYAIcKG&ust=1710902940307000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCKDtnqKo_4QDFQAAAAAdAAAAABAQ",
                "test5",
                "위치",
                "10",
                "2.0",
                30.450701,
                150.570667
            ),
            StoreSearchResult(
                6,
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.tripadvisor.co.kr%2FLocationPhotoDirectLink-g297390-d9861395-i294605803-Fresh_Fruit_Factory-Siem_Reap_Siem_Reap_Province.html&psig=AOvVaw1seb0YPuW-R_cQhQYAIcKG&ust=1710902940307000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCKDtnqKo_4QDFQAAAAAdAAAAABAQ",
                "test6",
                "위치",
                "0",
                "5.0",
                27.450701,
                100.570667
            )
        )
    )
    val searchResultList: LiveData<List<StoreSearchResult>> get() = _searchResultList

    private val _selectedStoreId: MutableLiveData<Int> = MutableLiveData()
    val selectedStoreId: LiveData<Int> get() = _selectedStoreId

    private val _startSearch: MutableLiveData<Boolean> = MutableLiveData(false)
    val startSearch: LiveData<Boolean> get() = _startSearch

    private val _currentLocation: MutableLiveData<Position> = MutableLiveData(Position(0.0, 0.0))
    val currentLocation: LiveData<Position> get() = _currentLocation

    fun updateCurrentLocation(position: Position) {
        _currentLocation.value = position
    }

    fun updateSelectedStoreId(storeId: Int) {
        _selectedStoreId.value = storeId
        Log.e("kang", "storeId $storeId")
    }

    fun getResult() {
        _startSearch.value = true
    }
}
