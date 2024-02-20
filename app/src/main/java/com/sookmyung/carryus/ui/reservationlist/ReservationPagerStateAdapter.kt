package com.sookmyung.carryus.ui.reservationlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sookmyung.carryus.domain.entity.ReservationStatus

class ReservationPagerStateAdapter (fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    companion object {
        const val POSITION_ACCEPTED = 0
        const val POSITION_WAITING = 1
        const val POSITION_COMPLETED = 2
        const val POSITION_CANCELED = 3
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ReservationPagerFragment()

        val arguments = Bundle()
        when (position) {
            POSITION_ACCEPTED -> arguments.putString("content", ReservationStatus.ACCEPTED.name)
            POSITION_WAITING -> arguments.putString("content", ReservationStatus.WAITING.name)
            POSITION_COMPLETED -> arguments.putString("content", ReservationStatus.COMPLETED.name)
            POSITION_CANCELED -> arguments.putString("content", ReservationStatus.CANCELED.name)
        }

        fragment.arguments = arguments
        return fragment
    }

}
