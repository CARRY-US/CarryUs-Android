package com.sookmyung.carryus.ui.search.storedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ItemMypageReviewBinding
import com.sookmyung.carryus.databinding.ItemStoreDetailReviewBinding
import com.sookmyung.carryus.domain.entity.StoreReview
import com.sookmyung.carryus.util.ItemDiffCallback

class StoreDetailReviewAdapter :
    ListAdapter<StoreReview, StoreDetailReviewAdapter.StoreDetailReviewViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreDetailReviewViewHolder {
        val itemStoreDetailReviewBinding =
            ItemStoreDetailReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return StoreDetailReviewViewHolder(itemStoreDetailReviewBinding)
    }

    override fun onBindViewHolder(holder: StoreDetailReviewViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class StoreDetailReviewViewHolder(
        val binding: ItemStoreDetailReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            data: StoreReview
        ) {
            binding.data = data
            setStarRating(data.reviewRating.toFloat())
        }

        private fun setStarRating(rating: Float) {
            val starImageViewIds = arrayOf(
                binding.ivItemStoreDetailStarOne,
                binding.ivItemStoreDetailStarTwo,
                binding.ivItemStoreDetailStartThree,
                binding.ivItemStoreDetailStarFour,
                binding.ivItemStoreDetailStarFive
            )

            val fullStarDrawable = R.drawable.ic_star_small_full
            val halfStarDrawable = R.drawable.ic_star_small_half
            val emptyStarDrawable = R.drawable.ic_star_small_gray

            val fullStarCount = rating.toInt()
            val hasHalfStar = rating - fullStarCount >= 0.5f

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
        private val DIFF_CALLBACK = ItemDiffCallback<StoreReview>(
            onItemsTheSame = { old, new -> old.reviewId == new.reviewId },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}
