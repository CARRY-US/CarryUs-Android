package com.sookmyung.carryus.ui.search.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivitySearchListBinding
import com.sookmyung.carryus.domain.entity.StoreSearchResult
import com.sookmyung.carryus.ui.search.storedetail.StoreDetailActivity
import com.sookmyung.carryus.ui.search.storedetail.StoreDetailActivity.Companion.STORE_ID
import com.sookmyung.carryus.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchListActivity :
    BindingActivity<ActivitySearchListBinding>(R.layout.activity_search_list) {
    private val viewModel by viewModels<SearchListViewModel>()
    private val searchResultAdapter: SearchResultAdapter?
        get() = binding.rvSearchListStoreList.adapter as? SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        getBundleData()
        setSearchResultAdapter()
        setSearchResultObserver()
        moveToStoreDetail()
    }

    private fun getBundleData() {
        val bundle = intent.getBundleExtra("searchStoreList")
        val searchStoreList = bundle?.getParcelableArrayList<StoreSearchResult>("searchStoreList")

        if (searchStoreList != null) {
            viewModel.updateSearchStoreList(searchStoreList)
        } else {
            viewModel.updateSearchStoreList(emptyList())
        }
    }

    private fun setSearchResultAdapter() {
        binding.rvSearchListStoreList.adapter = SearchResultAdapter { _, item ->
            viewModel.updateSelectedStoreId(item.storeId)
        }
    }

    private fun setSearchResultObserver() {
        binding.viewModel?.searchStoreList?.observe(this) { storeList ->
            searchResultAdapter?.submitList(storeList)
        }
    }

    private fun moveToStoreDetail() {
        viewModel.selectedStoreId.observe(this) {
            val toStoreDetail = Intent(this, StoreDetailActivity::class.java).putExtra(
                STORE_ID,
                viewModel.selectedStoreId.value
            )
            startActivity(toStoreDetail)
        }
    }
}
