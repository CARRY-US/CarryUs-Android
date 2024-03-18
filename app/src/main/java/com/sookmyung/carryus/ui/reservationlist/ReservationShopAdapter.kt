package com.sookmyung.carryus.ui.reservationlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sookmyung.carryus.databinding.ItemShopReservationListBinding
import com.sookmyung.carryus.domain.entity.ReservationList

class ReservationShopAdapter(private val reservationListClickListener: ReservationListClickListener) :
    ListAdapter<ReservationList, ReservationShopAdapter.ReservationShopViewHolder>(ReservationListDataCallback) {

    private lateinit var itemBinding: ItemShopReservationListBinding

    class ReservationShopViewHolder(
        private val itemBinding: ItemShopReservationListBinding,
        private var reservationListClickListener: ReservationListClickListener
    ): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(data:ReservationList) {
            with(itemBinding) {
                item = data
                clickListener = reservationListClickListener
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
