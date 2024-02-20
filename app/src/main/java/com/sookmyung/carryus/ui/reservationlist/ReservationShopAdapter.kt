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
            itemBinding.tvShopName.text = data.shopName
            itemBinding.tvShopLocation.text = data.shopLocation
            itemBinding.tvReservationDate.text = data.reservationDate
            itemBinding.ivShopIcon.setImageResource(R.drawable.ic_shop_img)
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
