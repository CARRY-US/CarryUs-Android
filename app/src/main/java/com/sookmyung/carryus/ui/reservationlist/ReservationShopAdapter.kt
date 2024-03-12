package com.sookmyung.carryus.ui.reservationlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ItemShopReservationListBinding
import com.sookmyung.carryus.domain.entity.ReservationList

class ReservationShopAdapter(private val reservationListClickListener: ReservationListClickListener) :
    ListAdapter<ReservationList, ReservationShopAdapter.ReservationShopViewHolder>(ReservationListDataCallback) {

    private lateinit var itemBinding: ItemShopReservationListBinding

    init {

    }
    class ReservationShopViewHolder(
        private val itemBinding: ItemShopReservationListBinding,
        private var reservationListClickListener: ReservationListClickListener
    ): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(data:ReservationList) {
            with(itemBinding) {
                item = data
                clickListener = reservationListClickListener
//                val truncatedShopName = if (data.shopName.length > 14) {
//                    "${data.shopName.substring(0, 14)}..."
//                } else {
//                    data.shopName
//                }
//
//                val truncatedShopLocation = if (data.shopLocation.length > 14) {
//                    "${data.shopLocation.substring(0, 14)}..."
//                } else {
//                    data.shopLocation
//                }
//
//                tvShopName.text = truncatedShopName
//                tvShopLocation.text = truncatedShopLocation
//                tvReservationDate.text = data.reservationDate
//                ivShopIcon.setImageResource(R.drawable.ic_shop_img)
            }
        }
    }

    companion object {
        private val ReservationListDataCallback = object : DiffUtil.ItemCallback<ReservationList>() {
            override fun areItemsTheSame(
                oldItem: ReservationList,
                newItem: ReservationList
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(
                oldItem: ReservationList,
                newItem: ReservationList
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationShopViewHolder {
        itemBinding = ItemShopReservationListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservationShopViewHolder(itemBinding, reservationListClickListener)
    }

    override fun onBindViewHolder(holder: ReservationShopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
class ReservationListClickListener(val clickListener: (reservationList: ReservationList) -> Unit) {
    fun onClick(reservationList: ReservationList) = clickListener(reservationList)
}
