package com.sookmyung.carryus.ui.reservationlist

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ReservationPagerStateAdapter (fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    // FragmentStateAdapter 상속 시 무조건 override 해야하는 fun
    override fun getItemCount(): Int {
        return 4
    }

    // FragmentStateAdapter 상속 시 무조건 override 해야하는 fun (View Pager의 position에 해당하는 fragment return)
    override fun createFragment(position: Int): Fragment {
        return ReservationPagerFragment()
    }
}
