package com.sookmyung.carryus.ui.review

import android.os.Bundle
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivityReviewEditBinding
import com.sookmyung.carryus.domain.entity.ReservationList
import com.sookmyung.carryus.domain.entity.ReviewDetail
import com.sookmyung.carryus.util.binding.BindingActivity


class ReviewEditActivity : BindingActivity<ActivityReviewEditBinding>(R.layout.activity_review_edit) {
    private val viewModel: ReviewEditViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setTextCount()
        setReviewDetailData()
        setReservationDetailData()
        setSaveBtnAction()
    }
    private fun setTextCount(){
        viewModel.textCount.observe(this) { count ->
            binding.tvReviewEditTextCount.text = count
        }
    }
    private fun setReviewDetailData(){
        viewModel.setReviewDetail(
            ReviewDetail(1,4.5f,"캐리어스 짱짱~")
        )
    }

    private fun setReservationDetailData(){
        viewModel.setReservationList(
            ReservationList(1,"shopimg","가게이름 최대 14자","위치 최대 18자 노출되고 나머지는 ...","2024.02.10 14:00 예약")
        )
    }

    private fun setSaveBtnAction(){
        binding.tvReviewEditSaveButton.setOnClickListener {
            viewModel.requestCloseActivity()
        }

        viewModel.closeActivityEvent.observe(this) {
            finish()
        }

    }
}
