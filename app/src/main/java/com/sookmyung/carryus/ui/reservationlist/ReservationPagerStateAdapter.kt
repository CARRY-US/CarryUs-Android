package com.sookmyung.carryus.ui.reservationlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sookmyung.carryus.domain.entity.ReservationStatus

class ReservationPagerStateAdapter (fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ReservationPagerFragment()

        val arguments = Bundle()
        when (position) {
            0 -> arguments.putString("content", ReservationStatus.ACCEPTED.name)
            1 -> arguments.putString("content", ReservationStatus.WAITING.name)
            2 -> arguments.putString("content", ReservationStatus.COMPLETED.name)
            3 -> arguments.putString("content", ReservationStatus.CANCELED.name)
        }

        fragment.arguments = arguments
        return fragment
    }
}
