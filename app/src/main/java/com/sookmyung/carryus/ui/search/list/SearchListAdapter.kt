package com.sookmyung.carryus.ui.search.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sookmyung.carryus.databinding.ItemSearchResultStoreBinding
import com.sookmyung.carryus.domain.entity.SimpleStoreReviewInfo
import com.sookmyung.carryus.util.ItemDiffCallback

class SearchResultAdapter(private val clickListener: ItemClickListener<SimpleStoreReviewInfo>) :
    ListAdapter<SimpleStoreReviewInfo, SearchResultAdapter.SearchResultViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val itemSearchResultStoreBinding =
            ItemSearchResultStoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return SearchResultViewHolder(itemSearchResultStoreBinding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.onBind(getItem(position), clickListener)
    }

    class SearchResultViewHolder(
        val binding: ItemSearchResultStoreBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(
            data: SimpleStoreReviewInfo,
            itemClickListener: ItemClickListener<SimpleStoreReviewInfo>
        ) {
            binding.data = data
            binding.root.setOnClickListener {
                itemClickListener.onClick(absoluteAdapterPosition, data)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = ItemDiffCallback<SimpleStoreReviewInfo>(
            onItemsTheSame = { old, new -> old.storeTitle == new.storeTitle },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}

fun interface ItemClickListener<T> {
    fun onClick(pos: Int, item: T)
}
