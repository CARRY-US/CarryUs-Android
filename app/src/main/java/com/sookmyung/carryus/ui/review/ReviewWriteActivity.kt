package com.sookmyung.carryus.ui.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivityReviewWriteBinding
import com.sookmyung.carryus.domain.entity.ReservationList
import com.sookmyung.carryus.domain.entity.ReviewDetail
import com.sookmyung.carryus.util.binding.BindingActivity

class ReviewWriteActivity : BindingActivity<ActivityReviewWriteBinding>(R.layout.activity_review_write) {
    private val viewModel: ReviewWriteViewModel by viewModels()
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
            binding.tvReviewWriteTextCount.text = count
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
        binding.tvReviewWriteSaveButton.setOnClickListener {
            viewModel.requestCloseActivity()
        }

        // ViewModel에서 종료 이벤트를 구독하여 액티비티 종료
        viewModel.closeActivityEvent.observe(this) {
            finish()
        }

    }
}
