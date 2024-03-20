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

        binding.viewModel = viewModel
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
        setReservationCheckBtnClickListener()
//        setSuitcaseCountClickListener()
    }

    private fun setReservationCheckBtnClickListener() {
        with(binding) {
            btnReservationRequestInitialize.setOnClickListener {
                clReservationRequestReservation.visibility = View.GONE
                clReservationRequestPayment.visibility = View.GONE
            }
            btnReservationRequestCheck.setOnClickListener {
                clReservationRequestReservation.visibility = View.VISIBLE
                clReservationRequestPayment.visibility = View.VISIBLE
            }
        }
    }

//    private fun setSuitcaseCountClickListener() {
//        with(binding) {
//            btnReservationRequestExtraSmallMinus.setOnClickListener { viewModel?.clickSuitCase(1, -1) }
//            btnReservationRequestExtraSmallPlus.setOnClickListener { viewModel?.clickSuitCase(1, 1) }
//            btnReservationRequestSmallMinus.setOnClickListener { viewModel?.clickSuitCase(2, -1) }
//            btnReservationRequestSmallPlus.setOnClickListener { viewModel?.clickSuitCase(2, 1) }
//            btnReservationRequestLargeMinus.setOnClickListener { viewModel?.clickSuitCase(3, -1) }
//            btnReservationRequestLargePlus.setOnClickListener { viewModel?.clickSuitCase(3, 1) }
//            btnReservationRequestExtraLargeMinus.setOnClickListener { viewModel?.clickSuitCase(4, -1) }
//            btnReservationRequestExtraLargePlus.setOnClickListener { viewModel?.clickSuitCase(4, 1) }
//        }
//    }

    companion object {
        const val TAG = "TAG"
        const val RESERVATION_TAG = "RESERVATION"
    }
}
