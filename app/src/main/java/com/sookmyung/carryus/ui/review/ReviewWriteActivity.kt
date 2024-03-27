package com.sookmyung.carryus.ui.review

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.sookmyung.carryus.R
import com.sookmyung.carryus.data.entitiy.request.ReviewRequest
import com.sookmyung.carryus.databinding.ActivityReviewWriteBinding
import com.sookmyung.carryus.domain.entity.ReservationList
import com.sookmyung.carryus.ui.reservationlist.ReservationPagerFragment.Companion.RESERVATION_INFO
import com.sookmyung.carryus.ui.reservationlist.detail.ReservationDetailActivity.Companion.MAXIMUM_LENGTH
import com.sookmyung.carryus.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewWriteActivity : BindingActivity<ActivityReviewWriteBinding>(R.layout.activity_review_write) {
    private val viewModel: ReviewWriteViewModel by viewModels()

    private var reservationId: Int = 0
    private var reservationInfo : ReservationList? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        setTextCount()
        setReservationDetailData()
        setSaveBtnAction()
    }

    private fun setTextCount(){
        viewModel.setReviewContent(binding.etReviewWriteContent.text.toString())

        viewModel.reviewContent.observe(this) { text ->
            binding.tvReviewWriteTextCount.text = "${text.length}/$MAXIMUM_LENGTH"
        }
    }

    private fun setReservationDetailData(){
        reservationInfo = intent.getParcelableExtra(RESERVATION_INFO) as ReservationList?
        reservationInfo?.let { viewModel.setReservationList(it) }
    }

    private fun setSaveBtnAction(){
        binding.tvReviewWriteSaveButton.setOnClickListener {
            val reviewRequest = ReviewRequest(
                binding.rbStarPoint.rating.toDouble(),
                binding.etReviewWriteContent.text.toString()
            )
            viewModel.postReview(reservationId,reviewRequest)
        }

        viewModel.closeActivityEvent.observe(this) {
            finish()
        }

    }
}
