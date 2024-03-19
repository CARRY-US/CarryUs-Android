package com.sookmyung.carryus.ui.mypage

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.FragmentMyPageBinding
import com.sookmyung.carryus.domain.entity.MyReviews
import com.sookmyung.carryus.util.dpToPx
import com.sookmyung.carryus.util.binding.BindingFragment

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

        setReviewList()
    }

    private fun setReviewList(){
        binding.rvMypageReview.apply{
            adapter = mypageReviewAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(RecyclerViewSpaceItemDecoration(18, 8))

        }
        mypageReviewAdapter.submitList(data)
    }
}

class RecyclerViewSpaceItemDecoration(private val startSpaceDp: Int, private val itemSpaceDp: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val context = view.context

        // dp를 픽셀로 변환
        val startSpacePx = context.dpToPx(startSpaceDp)
        val itemSpacePx = context.dpToPx(itemSpaceDp)

        // 첫 번째 아이템일 때 왼쪽 간격 설정
        if (position == 0) {
            outRect.left = startSpacePx
        }

        // 나머지 아이템 간격 설정
        outRect.right = itemSpacePx
    }
}


