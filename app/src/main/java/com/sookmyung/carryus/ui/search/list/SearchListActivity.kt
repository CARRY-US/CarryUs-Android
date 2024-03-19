package com.sookmyung.carryus.ui.search.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivitySearchListBinding
import com.sookmyung.carryus.ui.search.SearchViewModel
import com.sookmyung.carryus.ui.search.storedetail.StoreDetailActivity
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
        moveToStoreDetail()
    }

    private fun setSearchResultAdapter() {
        binding.rvSearchListStoreList.adapter = SearchResultAdapter { _, item ->
            viewModel.updateSelectedStoreId(item.storeId)
        }
    }

    private fun setSearchResultObserver() {
        viewModel.searchStoreList.observe(this) { list ->
            searchResultAdapter?.submitList(list)
        }
    }

    private fun moveToStoreDetail(){
        viewModel.selectedStoreId.observe(this){
            val toStoreDetail = Intent(this, StoreDetailActivity::class.java).putExtra("storeId", viewModel.selectedStoreId.value)
            startActivity(toStoreDetail)
        }
    }
}
