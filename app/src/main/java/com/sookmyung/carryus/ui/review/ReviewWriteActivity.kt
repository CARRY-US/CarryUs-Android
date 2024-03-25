package com.sookmyung.carryus.ui.review

import android.os.Bundle
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.data.entitiy.request.ReviewRequest
import com.sookmyung.carryus.databinding.ActivityReviewWriteBinding
import com.sookmyung.carryus.domain.entity.ReservationList
import com.sookmyung.carryus.domain.entity.ReviewDetail
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
        setReviewDetailData()
        setSaveBtnAction()
    }

    private fun setTextCount(){
        viewModel.textCount.observe(this) { count ->
            binding.tvReviewWriteTextCount.text = count
        }
    }
    private fun setReservationDetailData(){
        reservationId = intent.getIntExtra("reservation_id",0)

        viewModel.setReservationList(
            ReservationList(1,"shopimg","가게이름 최대 14자","위치 최대 18자 노출되고 나머지는 ...","2024.02.10 14:00 예약")
        )
    }

    private fun setReviewDetailData(){
        viewModel.initializeDataSet()
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
