package com.sookmyung.carryus.ui.search.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sookmyung.carryus.databinding.ItemSearchResultStoreBinding
import com.sookmyung.carryus.domain.entity.StoreSearchResult
import com.sookmyung.carryus.util.ItemDiffCallback

class SearchResultAdapter(private val clickListener: ItemClickListener<StoreSearchResult>) :
    ListAdapter<StoreSearchResult, SearchResultAdapter.SearchResultViewHolder>(DIFF_CALLBACK) {
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
            data: StoreSearchResult,
            itemClickListener: ItemClickListener<StoreSearchResult>
        ) {
            binding.data = data
            binding.root.setOnClickListener {
                itemClickListener.onClick(absoluteAdapterPosition, data)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = ItemDiffCallback<StoreSearchResult>(
            onItemsTheSame = { old, new -> old.storeId == new.storeId },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}

fun interface ItemClickListener<T> {
    fun onClick(pos: Int, item: T)
}
