package com.sookmyung.carryus.ui.search.reservationrequest

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivityReservationRequestBinding
import com.sookmyung.carryus.util.binding.BindingActivity

class ReservationRequestActivity :
    BindingActivity<ActivityReservationRequestBinding>(R.layout.activity_reservation_request) {
    private val viewModel by viewModels<ReservationRequestViewModel>()
    private val reservationRequestTimeAdapter: ReservationRequestTimeAdapter?
        get() = binding.rvReservationRequestTime.adapter as? ReservationRequestTimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvReservationRequestTime.adapter = ReservationRequestTimeAdapter { pos, item ->
            Log.e("stellar", "$pos, $item")
        }

        viewModel.reservationRequestAvailableTimeList.observe(this){
            reservationRequestTimeAdapter?.submitList(viewModel.reservationRequestTimeList)
        }
    }
}
