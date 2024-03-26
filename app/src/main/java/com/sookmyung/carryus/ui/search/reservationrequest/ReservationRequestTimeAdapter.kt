package com.sookmyung.carryus.ui.search.reservationrequest

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sookmyung.carryus.databinding.ItemReservationRequestTimeBinding
import com.sookmyung.carryus.domain.entity.Time
import com.sookmyung.carryus.util.ItemDiffCallback


class ReservationRequestTimeAdapter(private val clickListener: ItemClickListener<Time>) :
    ListAdapter<Time, ReservationRequestTimeAdapter.ReservationRequestTimeViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservationRequestTimeViewHolder {
        val itemReservationRequestTimeBinding =
            ItemReservationRequestTimeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ReservationRequestTimeViewHolder(itemReservationRequestTimeBinding)
    }

    override fun onBindViewHolder(holder: ReservationRequestTimeViewHolder, position: Int) {
        holder.onBind(getItem(position), clickListener)
    }

    fun listChange(start: Int, end: Int) {
        for (idx in start..end) {
            notifyItemChanged(idx)
        }
    }

    class ReservationRequestTimeViewHolder(
        val binding: ItemReservationRequestTimeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("ResourceAsColor")
        fun onBind(
            data: Time,
            itemClickListener: ItemClickListener<Time>
        ) {
            binding.data = data
            binding.root.setOnClickListener {
                itemClickListener.onClick(absoluteAdapterPosition, data)
                if (data.available) {
                    val newData = data.copy(select = !(data.select))
                    binding.data = newData
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = ItemDiffCallback<Time>(
            onItemsTheSame = { old, new -> old.timeId == new.timeId },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}

fun interface ItemClickListener<T> {
    fun onClick(pos: Int, item: T)
}
