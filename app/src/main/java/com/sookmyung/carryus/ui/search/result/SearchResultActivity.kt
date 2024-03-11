package com.sookmyung.carryus.ui.search.result

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivitiySearchResultBinding
import com.sookmyung.carryus.util.binding.BindingActivity

class SearchResultActivity :
    BindingActivity<ActivitiySearchResultBinding>(R.layout.activitiy_search_result) {
    private val viewModel by viewModels<SearchResultViewModel>()
    private val searchResultAdapter: SearchResultAdapter?
        get() = binding.rvSearchResultStoreList.adapter as? SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        binding.rvSearchResultStoreList.adapter = SearchResultAdapter { _, item ->
            viewModel.updateSelectedStoreId(item.storeTitle)
        }

        viewModel.searchResultList.observe(this) { list ->
            searchResultAdapter?.submitList(list)
        }

        binding.tvSearchResultSearch.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                viewModel.getResult()
                return@setOnKeyListener true
            }
            false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
