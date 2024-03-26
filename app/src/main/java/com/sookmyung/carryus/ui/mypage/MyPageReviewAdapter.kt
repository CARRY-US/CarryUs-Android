package com.sookmyung.carryus.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ItemMypageReviewBinding
import com.sookmyung.carryus.domain.entity.MyReviews

class MyPageReviewAdapter(private val myPageReviewClickListener: MyPageReviewClickListener) :
    ListAdapter<MyReviews, MyPageReviewAdapter.MyPageReviewViewHolder>(ReservationListDataCallback) {

    private lateinit var itemBinding: ItemMypageReviewBinding

    class MyPageReviewViewHolder(
        private val itemBinding: ItemMypageReviewBinding,
        private var myPageReviewClickListener: MyPageReviewClickListener
    ): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(data: MyReviews) {
            with(itemBinding) {
                item = data
                clickListener = myPageReviewClickListener

                setStarRating(data.reviewRating.toFloat())
            }
        }

        fun setStarRating(rating: Float) {
            val starImageViewIds = arrayOf(
                itemBinding.ivMypageReviewStarOne,
                itemBinding.ivMypageReviewStarTwo,
                itemBinding.ivMypageReviewStarThree,
                itemBinding.ivMypageReviewStarFour,
                itemBinding.ivMypageReviewStarFive
            )

            val fullStarDrawable = R.drawable.ic_star_small_full
            val halfStarDrawable = R.drawable.ic_star_small_half
            val emptyStarDrawable = R.drawable.ic_star_small_gray

            // 채워진 별의 개수를 계산
            val fullStarCount = rating.toInt()
            // 소수점 부분에 따라 반채워진 별이나 빈 별을 표시할지 결정
            val hasHalfStar = rating - fullStarCount >= 0.5f

            // 각 별 이미지뷰에 적절한 별 이미지를 설정
            for (i in starImageViewIds.indices) {
                val imageView = starImageViewIds[i]
                when {
                    i < fullStarCount -> imageView.setImageResource(fullStarDrawable)
                    i == fullStarCount && hasHalfStar -> imageView.setImageResource(halfStarDrawable)
                    else -> imageView.setImageResource(emptyStarDrawable)
                }
            }
        }
    }

    companion object {
        private val ReservationListDataCallback = object : DiffUtil.ItemCallback<MyReviews>() {
            override fun areItemsTheSame(
                oldItem: MyReviews,
                newItem: MyReviews
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(
                oldItem: MyReviews,
                newItem: MyReviews
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageReviewViewHolder {
        itemBinding = ItemMypageReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPageReviewViewHolder(itemBinding, myPageReviewClickListener)
    }

    override fun onBindViewHolder(holder: MyPageReviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
class MyPageReviewClickListener(val clickListener: (myReviews: MyReviews) -> Unit) {
    fun onClick(myReviews: MyReviews) = clickListener(myReviews)
}
