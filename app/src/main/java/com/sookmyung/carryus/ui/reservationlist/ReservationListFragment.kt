package com.sookmyung.carryus.ui.reservationlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.FragmentReservationListBinding
import com.sookmyung.carryus.util.binding.BindingFragment

class ReservationListFragment :
    BindingFragment<FragmentReservationListBinding>(R.layout.fragment_reservation_list) {

    private val viewModel: ReservationListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserveTabItems()
    }

    private fun setObserveTabItems() {
        viewModel.tabItems.observe(viewLifecycleOwner, Observer {
            setupTabLayout(it)
        })
    }

    private fun setupTabLayout(tabItems: List<String>) {
        val tabLayout = binding.tabLayout
        val viewPager2 = binding.viewPager

        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val viewPager2Adapter = ReservationPagerStateAdapter(requireActivity())

        viewPager2.adapter = viewPager2Adapter
        TabLayoutMediator(tabLayout!!, viewPager2) { tab, position ->
            tab.text = tabItems[position]
        }.attach()
    }
}
