package com.sookmyung.carryus.ui.main

import android.os.Bundle
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivityMainBinding
import com.sookmyung.carryus.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
