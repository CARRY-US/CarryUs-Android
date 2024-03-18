package com.sookmyung.carryus.ui.search.storedetail

import android.os.Bundle
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivityStoreDetailBinding
import com.sookmyung.carryus.ui.search.list.SearchResultAdapter
import com.sookmyung.carryus.util.binding.BindingActivity

class StoreDetailActivity :
    BindingActivity<ActivityStoreDetailBinding>(R.layout.activity_store_detail) {
    private val viewModel by viewModels<StoreDetailViewModel>()
    private val storeDetailReviewAdapter: StoreDetailReviewAdapter?
        get() = binding.rvStoreDetailReview.adapter as? StoreDetailReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        viewModel.getStoreInfo()
        setStoreDetailReviewAdapter()
        setReviewObserver()
    }

    private fun setStoreDetailReviewAdapter() {
        binding.rvStoreDetailReview.adapter = SearchResultAdapter()
    }

    private fun setReviewObserver() {
        viewModel.reviewList.observe(this) { list ->
            storeDetailReviewAdapter?.submitList(list)
        }
    }
}
