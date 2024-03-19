package com.sookmyung.carryus.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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
