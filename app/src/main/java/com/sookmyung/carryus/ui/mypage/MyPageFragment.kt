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

    var data = listOf(
        MyReviews(1,"가게이름",4.5,"정말 최고예요! 거리도 좋고 접근성도 뛰어나서 짐 맡기기 너무 편리했어요 :)"),
        MyReviews(2,"가게이름",4.5,"정말 최고예요! 거리도 좋고 접근성도 뛰어나서 짐 맡기기 너무 편리했어요 :)"),
        MyReviews(3,"가게이름",4.5,"정말 최고예요! 거리도 좋고 접근성도 뛰어나서 짐 맡기기 너무 편리했어요 :)")
    )

    private val mypageReviewAdapter by lazy {
        MyPageReviewAdapter(MyPageReviewClickListener { clickedReview ->
            viewModel.onReservationItemClick(clickedReview)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setMyProfile()
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

    private fun setReviewList(){
        binding.rvMypageReview.apply{
            adapter = mypageReviewAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(RecyclerViewSpaceItemDecoration(18, 8))
        }
        mypageReviewAdapter.submitList(data)
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
        intent.putExtra("review_id", myReviews.reviewId)
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


