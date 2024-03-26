package com.sookmyung.carryus.ui.search.storedetail

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivityStoreDetailBinding
import com.sookmyung.carryus.ui.search.reservationrequest.ReservationRequestActivity
import com.sookmyung.carryus.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    private fun getStoreId() {
        val storeId = intent.getIntExtra(STORE_ID, 0)
        viewModel.updateStoreId(storeId)
    }

    private fun setStoreDetailReviewAdapter() {
        binding.rvStoreDetailReview.adapter = StoreDetailReviewAdapter()
        setReviewObserver()
    }

    private fun setReviewObserver() {
        viewModel.storeDetailReview.observe(this) { review ->
            storeDetailReviewAdapter?.submitList(review.reviewList)
            setStarRating()
            setRecyclerviewHeight()
        }
    }

    private fun setRecyclerviewHeight() {
        val layoutParams = binding.rvStoreDetailReview.layoutParams
        if (viewModel.storeDetailReview.value?.reviewList?.size!! > 5) layoutParams.height = 1000
        else layoutParams.height = 0
        binding.rvStoreDetailReview.layoutParams = layoutParams
    }

    private fun moveToReservationRequest() {
        binding.btnStoreDetailRequest.setOnClickListener {
            val moveToReservationRequestIntent =
                Intent(this, ReservationRequestActivity::class.java)
            val bundle = Bundle()
            val suitCaseInfoList =
                ArrayList<Parcelable>(viewModel.storeInfo.value?.baggageTypeInfoList ?: emptyList())
            bundle.putParcelableArrayList(SUITCASE_FEE, suitCaseInfoList)
            moveToReservationRequestIntent.putExtra(SUITCASE_FEE, bundle)
            moveToReservationRequestIntent.putExtra(STORE_ID, viewModel.storeId.value)
            startActivity(moveToReservationRequestIntent)
        }
    }

    private fun setStarRating() {
        val starImageViewIds = arrayOf(
            binding.ivStoreDetailStarOne,
            binding.ivStoreDetailStarTwo,
            binding.ivStoreDetailStarThree,
            binding.ivStoreDetailStarFour,
            binding.ivStoreDetailStarFive
        )
        val fullStarDrawable = R.drawable.ic_star_medium_full
        val halfStarDrawable = R.drawable.ic_star_medium_half
        val emptyStarDrawable = R.drawable.ic_star_medium_gray

        val ratingDouble = viewModel.storeDetailReview.value?.reviewRatingAverage ?: 0.0
        val fullStarCount = ratingDouble.toInt()
        val hasHalfStar = ratingDouble - fullStarCount >= 0.5f

        for (i in starImageViewIds.indices) {
            val imageView = starImageViewIds[i]
            when {
                i < fullStarCount -> imageView.setImageResource(fullStarDrawable)
                i == fullStarCount && hasHalfStar -> imageView.setImageResource(halfStarDrawable)
                else -> imageView.setImageResource(emptyStarDrawable)
            }
        }
    }

    companion object {
        const val STORE_ID = "STORE_ID"
        const val SUITCASE_FEE = "SUITCASE_FEE"
    }
}
