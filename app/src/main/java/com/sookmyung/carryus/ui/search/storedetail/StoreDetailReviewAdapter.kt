package com.sookmyung.carryus.ui.search.storedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sookmyung.carryus.databinding.ItemStoreDetailReviewBinding
import com.sookmyung.carryus.domain.entity.StoreReview
import com.sookmyung.carryus.util.ItemDiffCallback

class StoreDetailReviewAdapter(private val clickListener: ItemClickListener<StoreReview>) :
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
        holder.onBind(getItem(position), clickListener)
    }

    class StoreDetailReviewViewHolder(
        val binding: ItemStoreDetailReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(
            data: StoreReview,
            itemClickListener: ItemClickListener<StoreReview>
        ) {
            binding.data = data
            binding.root.setOnClickListener {
                itemClickListener.onClick(absoluteAdapterPosition, data)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = ItemDiffCallback<StoreReview>(
            onItemsTheSame = { old, new -> old.name == new.name },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}

fun interface ItemClickListener<T> {
    fun onClick(pos: Int, item: T)
}
