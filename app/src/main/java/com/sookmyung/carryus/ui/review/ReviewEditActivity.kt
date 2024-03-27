package com.sookmyung.carryus.ui.review

import android.os.Bundle
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivityReviewEditBinding
import com.sookmyung.carryus.ui.mypage.MyPageFragment.Companion.REVIEW_ID
import com.sookmyung.carryus.ui.reservationlist.detail.ReservationDetailActivity.Companion.MAXIMUM_LENGTH
import com.sookmyung.carryus.util.binding.BindingActivity
import com.sookmyung.carryus.util.binding.BindingAdapter.setImage
import com.sookmyung.carryus.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewEditActivity : BindingActivity<ActivityReviewEditBinding>(R.layout.activity_review_edit) {
    private val viewModel: ReviewEditViewModel by viewModels()

    private var reviewId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        reviewId = intent.getIntExtra(REVIEW_ID,0)

        setTextCount()
        setReviewDetailData()
        setReservationDetailData()
        setSaveBtnAction()
    }
    private fun setTextCount(){
        viewModel.setReviewContent(binding.etReviewEditContent.text.toString())

        viewModel.reviewContent.observe(this) { text ->
            val isTextEmpty = text.isNullOrEmpty()
            binding.tvReviewEditSaveButton.isEnabled = !isTextEmpty
            binding.tvReviewEditTextCount.text = "${text.length}/$MAXIMUM_LENGTH"
        }
    }
    private fun setReviewDetailData(){
        viewModel.setReviewDetail(reviewId)

        viewModel.reviewDetailLiveData.observe(this) { reviewDetail ->
            with(binding){
                rbReviewEditStarPoint.rating = reviewDetail.reviewRating.toFloat()
                etReviewEditContent.setText(reviewDetail.reviewContent)
            }
        }
        viewModel.rating.observe(this) { rating ->
            binding.rbReviewEditStarPoint.rating = rating
        }
    }

    private fun setReservationDetailData(){
        viewModel.setReviewStoreInfo(reviewId)

        viewModel.reviewStoreInfoLiveData.observe(this){reviewstoreInfo ->
            with(binding){
                ivReviewEditShopIcon.setImage(reviewstoreInfo.storeImgUrl)
                tvReviewEditShopName.text = reviewstoreInfo.storeName
                tvReviewEditShopLocation.text = reviewstoreInfo.storeLocation
                tvReviewEditReservationDate.text = reviewstoreInfo.reservationInfo
            }

        }
    }

    private fun setSaveBtnAction(){
        binding.tvReviewEditSaveButton.setOnClickListener {
            viewModel.updateReview(reviewId)

            viewModel.updateResultLiveData.observe(this) { result ->
                if(result){
                    applicationContext.toast("수정 성공")
                    finish()
                }else{
                    applicationContext.toast("수정 실패")
                }
            }
        }
    }
}
