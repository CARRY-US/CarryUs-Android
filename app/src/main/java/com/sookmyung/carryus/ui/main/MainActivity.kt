package com.sookmyung.carryus.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivityMainBinding
import com.sookmyung.carryus.ui.mypage.MyPageFragment
import com.sookmyung.carryus.ui.reservationlist.ReservationListFragment
import com.sookmyung.carryus.ui.search.SearchFragment
import com.sookmyung.carryus.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragment()
        initBottomNavigation()
        setBottomNavigationClickListener()
    }

    private fun initFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_main, SearchFragment())
                .commit()
        }
    }

    private fun changeFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
        return true
    }

    private fun initBottomNavigation() {
        binding.bnvMain.selectedItemId = R.id.bottom_navigation_search
    }

    private fun setBottomNavigationClickListener() {
        binding.bnvMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_navigation_search -> changeFragment(SearchFragment())
                R.id.bottom_navigation_list -> changeFragment(ReservationListFragment())
                R.id.bottom_navigation_my -> changeFragment(MyPageFragment())
                else -> true
            }
        }
    }
}
