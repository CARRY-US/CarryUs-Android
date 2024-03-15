package com.sookmyung.carryus.ui.search.list

import android.os.Bundle
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivitySearchListBinding
import com.sookmyung.carryus.ui.search.SearchViewModel
import com.sookmyung.carryus.util.binding.BindingActivity

class SearchListActivity :
    BindingActivity<ActivitySearchListBinding>(R.layout.activity_search_list) {
    private val viewModel by viewModels<SearchViewModel>()
    private val searchResultAdapter: SearchResultAdapter?
        get() = binding.rvSearchListStoreList.adapter as? SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setSearchResultAdapter()
        setSearchResultObserver()
    }

    private fun setSearchResultAdapter() {
        binding.rvSearchListStoreList.adapter = SearchResultAdapter { _, item ->
            viewModel.updateSelectedStoreId(item.storeTitle)
        }
    }

    private fun setSearchResultObserver() {
        viewModel.searchResultList.observe(this) { list ->
            searchResultAdapter?.submitList(list)
        }
    }
}
