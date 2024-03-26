package com.sookmyung.carryus.ui.mypage

import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.FragmentMyPageBinding
import com.sookmyung.carryus.domain.entity.MyReviews
import com.sookmyung.carryus.ui.reservationlist.detail.CustomDialog
import com.sookmyung.carryus.ui.review.ReviewInquiryActivity
import com.sookmyung.carryus.util.binding.BindingAdapter.setImage
import com.sookmyung.carryus.util.dpToPx
import com.sookmyung.carryus.util.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val viewModel: MyPageViewModel by viewModels()

    private val mypageReviewAdapter by lazy {
        MyPageReviewAdapter(MyPageReviewClickListener { clickedReview ->
            viewModel.onReservationItemClick(clickedReview)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setMyProfile()
        setReviewRecyclerView()
        setReviewList()
        setViewModelNavigate()
        setCancelDialog()
    }

    private fun setMyProfile(){
        viewModel.getMyProfile()
        viewModel.myProfile.observe(viewLifecycleOwner) { myProfile ->
            binding.ivMypageProfileImg.setImage(myProfile.memberProfileImg)
            binding.tvMypageMemberName.text = myProfile.memberName
        }
    }
    private fun setReviewRecyclerView(){
        binding.rvMypageReview.apply{
            adapter = mypageReviewAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(RecyclerViewSpaceItemDecoration(18, 8))
        }
    }

    private fun setReviewList(){
        viewModel.getMyReviews()
        viewModel.myReviews.observe(viewLifecycleOwner) { myReviews ->
            mypageReviewAdapter.submitList(myReviews)
        }
    }

    private fun setViewModelNavigate(){
        viewModel.navigateToDetail.observe(viewLifecycleOwner) { myReview ->
            myReview?.let {
                openCreateBuilding(it)
                viewModel.onNavigationComplete()
            }
        }
    }

    private fun openCreateBuilding(myReviews: MyReviews) {
        val intent = Intent(context, ReviewInquiryActivity::class.java)
        intent.putExtra(REVIEW_ID, myReviews.reviewId)
        startActivity(intent)
    }

    private fun setCancelDialog() {
        val customDialog = CustomDialog(requireActivity())
        val alertDialog = customDialog.create()

        customDialog.setTitle("로그아웃 하시겠습니까?")
        customDialog.setPositiveButton("로그아웃", View.OnClickListener {
            alertDialog.dismiss()
        })

        customDialog.setNegativeButton("취소", View.OnClickListener {
            alertDialog.dismiss()
        })
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        viewModel.showDialog.observe(requireActivity()) { showDialog ->
            if (showDialog) {
                alertDialog.show()
            } else {
                alertDialog.dismiss()
            }
        }
    }

    companion object{
        const val REVIEW_ID = "REVIEW_ID"
    }
}

class RecyclerViewSpaceItemDecoration(private val startSpaceDp: Int, private val itemSpaceDp: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val context = view.context

        val startSpacePx = context.dpToPx(startSpaceDp)
        val itemSpacePx = context.dpToPx(itemSpaceDp)

        if (position == 0) {
            outRect.left = startSpacePx
        }

        outRect.right = itemSpacePx
    }
}


