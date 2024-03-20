package com.sookmyung.carryus.ui.search.reservationrequest

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivityReservationRequestBinding
import com.sookmyung.carryus.ui.main.MainActivity
import com.sookmyung.carryus.util.binding.BindingActivity

class ReservationRequestActivity :
    BindingActivity<ActivityReservationRequestBinding>(R.layout.activity_reservation_request) {
    private val viewModel by viewModels<ReservationRequestViewModel>()
    private val reservationRequestTimeAdapter: ReservationRequestTimeAdapter?
        get() = binding.rvReservationRequestTime.adapter as? ReservationRequestTimeAdapter
    private lateinit var reservationRequestTimeItemDeco: ReservationRequestTimeItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTimeRecyclerAdapter()
        sendRequest()
        setClickListener()
    }

    private fun setTimeRecyclerAdapter() {
        binding.rvReservationRequestTime.adapter = ReservationRequestTimeAdapter { pos, _ ->
            viewModel.itemClick(pos)
            reservationRequestTimeAdapter?.itemClick(pos)
        }
        submitListTimeRecyclerAdapter()
        setTimeRecyclerItemDeco()
    }

    private fun submitListTimeRecyclerAdapter() {
        viewModel.reservationRequestAvailableTimeList.observe(this) {
            reservationRequestTimeAdapter?.submitList(viewModel.reservationRequestTimeList)
        }
    }

    private fun setTimeRecyclerItemDeco() {
        reservationRequestTimeItemDeco = ReservationRequestTimeItemDecoration(baseContext)
        binding.rvReservationRequestTime.addItemDecoration(reservationRequestTimeItemDeco)
    }

    private fun sendRequest() {
        binding.btnReservationRequestSend.setOnClickListener {
            val intentToReservationDetail = Intent(this, MainActivity::class.java)
            intentToReservationDetail.putExtra(TAG, RESERVATION_TAG)
            intentToReservationDetail.flags = FLAG_ACTIVITY_CLEAR_TOP or FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intentToReservationDetail)
        }
    }

    private fun setClickListener() {
        binding.btnReservationRequestInitialize.setOnClickListener {
            binding.clReservationRequestReservation.visibility = View.GONE
            binding.clReservationRequestPayment.visibility = View.GONE
        }
        binding.btnReservationRequestCheck.setOnClickListener {
            binding.clReservationRequestReservation.visibility = View.VISIBLE
            binding.clReservationRequestPayment.visibility = View.VISIBLE
        }
    }

    companion object {
        const val TAG = "TAG"
        const val RESERVATION_TAG = "RESERVATION"
    }
}
