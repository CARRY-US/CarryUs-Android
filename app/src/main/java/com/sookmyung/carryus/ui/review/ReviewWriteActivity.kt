package com.sookmyung.carryus.ui.review

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.data.entitiy.request.ReviewRequest
import com.sookmyung.carryus.databinding.ActivityReviewWriteBinding
import com.sookmyung.carryus.domain.entity.ReservationList
import com.sookmyung.carryus.domain.entity.ReviewDetail
import com.sookmyung.carryus.ui.reservationlist.detail.ReservationDetailActivity
import com.sookmyung.carryus.ui.reservationlist.detail.ReservationDetailActivity.Companion.MAXIMUM_LENGTH
import com.sookmyung.carryus.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewWriteActivity : BindingActivity<ActivityReviewWriteBinding>(R.layout.activity_review_write) {
    private val viewModel: ReviewWriteViewModel by viewModels()

    private var reservationId: Int = 0
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
        reservationId = intent.getIntExtra("reservation_id",0)

        viewModel.setReservationList(
            ReservationList(1,"shopimg","가게이름 최대 14자","위치 최대 18자 노출되고 나머지는 ...","2024.02.10 14:00 예약")
        )
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
