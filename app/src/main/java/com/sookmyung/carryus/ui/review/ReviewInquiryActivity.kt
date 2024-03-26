package com.sookmyung.carryus.ui.review

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivityReviewInquiryBinding
import com.sookmyung.carryus.ui.mypage.MyPageFragment
import com.sookmyung.carryus.util.binding.BindingActivity
import com.sookmyung.carryus.util.binding.BindingAdapter.setImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewInquiryActivity : BindingActivity<ActivityReviewInquiryBinding>(R.layout.activity_review_inquiry)
{
    private val viewModel: ReviewInquiryViewModel by viewModels()

    private var reviewId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        reviewId = intent.getIntExtra(MyPageFragment.REVIEW_ID,0)

        setReviewInquiryEdit()
        setReviewDetailData()
        setReservationDetailData()
    }
    private fun setReviewInquiryEdit(){
        binding.tvReviewInquiryEdit.setOnClickListener {
            val intent = Intent(this, ReviewEditActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setReviewDetailData(){
        viewModel.setReviewDetail(reviewId)

        viewModel.reviewDetailLiveData.observe(this) { reviewDetail ->
            with(binding){
                rbReviewInquiryStarPoint.rating = reviewDetail.reviewRating.toFloat()
                tvReviewInquiryContent.text = reviewDetail.reviewContent
            }
        }

    }

    private fun setReservationDetailData(){
        viewModel.setReservationList(reviewId)

        viewModel.reviewStoreInfoLiveData.observe(this){reviewstoreInfo ->
            with(binding){
                ivReviewInquiryShopIcon.setImage(reviewstoreInfo.storeImgUrl)
                tvReviewInquiryShopName.text = reviewstoreInfo.storeName
                tvReviewInquiryShopLocation.text = reviewstoreInfo.storeLocation
                tvReviewInquiryReservationDate.text = reviewstoreInfo.reservationInfo
            }

        }

    }


}
