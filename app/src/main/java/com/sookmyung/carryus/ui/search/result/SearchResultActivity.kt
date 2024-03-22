package com.sookmyung.carryus.ui.search.result

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivitySearchResultBinding
import com.sookmyung.carryus.ui.search.result.map.SearchResultMapActivity
import com.sookmyung.carryus.ui.search.storedetail.StoreDetailActivity
import com.sookmyung.carryus.ui.search.storedetail.StoreDetailActivity.Companion.STORE_ID
import com.sookmyung.carryus.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultActivity :
    BindingActivity<ActivitySearchResultBinding>(R.layout.activity_search_result) {
    private val viewModel by viewModels<SearchResultViewModel>()
    private val searchResultAdapter: SearchResultAdapter?
        get() = binding.rvSearchResultStoreList.adapter as? SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setSearchResultAdapter()
        setSearchResultObserver()
        pressEnter()
        setFabMapClickListener()
        moveToStoreDetail()
    }

    private fun setSearchResultAdapter() {
        binding.rvSearchResultStoreList.adapter = SearchResultAdapter { _, item ->
            viewModel.updateSelectedStoreId(item.storeId)
        }
    }

    private fun setSearchResultObserver() {
        viewModel.searchResultList.observe(this) { list ->
            searchResultAdapter?.submitList(list)
        }
    }

    private fun pressEnter() {
        binding.tvSearchResultSearch.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                viewModel.searchCityBase()
                return@setOnKeyListener true
            }
            false
        }
    }

    private fun setFabMapClickListener() {
        binding.fabSearchResultMap.setOnClickListener {
            val toSearchResultMap = Intent(this, SearchResultMapActivity::class.java)
            startActivity(toSearchResultMap)
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
