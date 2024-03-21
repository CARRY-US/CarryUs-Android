package com.sookmyung.carryus.ui.search.storedetail

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivityStoreDetailBinding
import com.sookmyung.carryus.ui.search.reservationrequest.ReservationRequestActivity
import com.sookmyung.carryus.util.binding.BindingActivity

class StoreDetailActivity :
    BindingActivity<ActivityStoreDetailBinding>(R.layout.activity_store_detail) {
    private val viewModel by viewModels<StoreDetailViewModel>()
    private val storeDetailReviewAdapter: StoreDetailReviewAdapter?
        get() = binding.rvStoreDetailReview.adapter as? StoreDetailReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        getStoreId()
        setStoreDetailReviewAdapter()
        moveToReservationRequest()
    }

    private fun getStoreId(){
        val storeId = intent.getIntExtra(STORE_ID,0)
        viewModel.updateStoreId(storeId)
    }

    private fun setStoreDetailReviewAdapter() {
        binding.rvStoreDetailReview.adapter = StoreDetailReviewAdapter()
        setReviewObserver()
        setRecyclerviewHeight()
    }

    private fun setReviewObserver() {
        viewModel.storeDetailReview.observe(this) { review ->
            storeDetailReviewAdapter?.submitList(review.reviewList)
        }
    }

    private fun setRecyclerviewHeight() {
        val layoutParams = binding.rvStoreDetailReview.layoutParams
        if (viewModel.storeDetailReview.value?.reviewList?.size!! > 5) layoutParams.height = 1000
        else layoutParams.height = 0
        binding.rvStoreDetailReview.layoutParams = layoutParams
    }

    private fun moveToReservationRequest(){
        binding.btnStoreDetailRequest.setOnClickListener {
            val moveToReservationRequestIntent = Intent(this, ReservationRequestActivity::class.java)
            moveToReservationRequestIntent.putExtra(STORE_ID, viewModel.storeId.value)
            startActivity(moveToReservationRequestIntent)
        }
    }

    companion object{
        const val STORE_ID = "STORE_ID"
    }
}
