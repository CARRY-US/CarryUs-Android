package com.sookmyung.carryus.ui.reservationlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.FragmentReservationListBinding
import com.sookmyung.carryus.util.binding.BindingFragment

class ReservationListFragment :
    BindingFragment<FragmentReservationListBinding>(R.layout.fragment_reservation_list) {

    private val viewModel: ReservationListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.tabItems.observe(viewLifecycleOwner, Observer { tabItems ->
            setupTabLayout(tabItems)
        })
    }

    private fun setupTabLayout(tabItems: List<String>) {
        val tabLayout = binding.tabLayout
        val viewPager2 = binding.viewPager

        val viewPager2Adapter = ReservationPagerStateAdapter(requireActivity())

        viewPager2.adapter = viewPager2Adapter
        TabLayoutMediator(tabLayout!!, viewPager2) { tab, position ->
            tab.text = tabItems[position]
        }.attach()
    }
}
