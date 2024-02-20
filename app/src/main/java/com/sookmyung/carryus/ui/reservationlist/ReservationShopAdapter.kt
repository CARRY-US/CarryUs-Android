package com.sookmyung.carryus.ui.reservationlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ItemShopReservationListBinding
import com.sookmyung.carryus.domain.entity.ReservationList

class ReservationShopAdapter(var reservationList: List<ReservationList>):
    RecyclerView.Adapter<ReservationShopAdapter.viewHolder>() {

    private lateinit var itemBinding: ItemShopReservationListBinding

    inner class viewHolder(private val itemBinding: ItemShopReservationListBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(data:ReservationList) {
            with(itemBinding) {
                val truncatedShopName = if (data.shopName.length > 14) {
                    "${data.shopName.substring(0, 14)}..."
                } else {
                    data.shopName
                }

                val truncatedShopLocation = if (data.shopLocation.length > 14) {
                    "${data.shopLocation.substring(0, 14)}..."
                } else {
                    data.shopLocation
                }

                tvShopName.text = truncatedShopName
                tvShopLocation.text = truncatedShopLocation
                tvReservationDate.text = data.reservationDate
                ivShopIcon.setImageResource(R.drawable.ic_shop_img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        itemBinding = ItemShopReservationListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(reservationList[position])
    }

    override fun getItemCount(): Int {
        return reservationList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}
