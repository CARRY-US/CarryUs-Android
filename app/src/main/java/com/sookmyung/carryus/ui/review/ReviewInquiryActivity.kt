package com.sookmyung.carryus.ui.review

import android.content.Intent
import android.os.Bundle
import com.sookmyung.carryus.R
import com.sookmyung.carryus.ReviewEditActivity
import com.sookmyung.carryus.databinding.ActivityReviewInquiryBinding
import com.sookmyung.carryus.util.binding.BindingActivity

class ReviewInquiryActivity : BindingActivity<ActivityReviewInquiryBinding>(R.layout.activity_review_inquiry)
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvReviewInquiryEdit.setOnClickListener {
            val intent = Intent(this, ReviewEditActivity::class.java)
            startActivity(intent)
        }
    }
}
