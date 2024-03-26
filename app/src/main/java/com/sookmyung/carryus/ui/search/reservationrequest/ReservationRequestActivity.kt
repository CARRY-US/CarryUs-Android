package com.sookmyung.carryus.ui.search.reservationrequest

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivityReservationRequestBinding
import com.sookmyung.carryus.domain.entity.BaggageTypeInfo
import com.sookmyung.carryus.domain.entity.StoreSearchResult
import com.sookmyung.carryus.ui.main.MainActivity
import com.sookmyung.carryus.ui.search.storedetail.StoreDetailActivity
import com.sookmyung.carryus.ui.search.storedetail.StoreDetailActivity.Companion.SUITCASE_FEE
import com.sookmyung.carryus.util.binding.BindingActivity
import com.sookmyung.carryus.util.binding.BindingAdapter.setFormattedPrice
import com.sookmyung.carryus.util.toast
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class ReservationRequestActivity :
    BindingActivity<ActivityReservationRequestBinding>(R.layout.activity_reservation_request) {
    private val viewModel by viewModels<ReservationRequestViewModel>()
    private val reservationRequestTimeAdapter: ReservationRequestTimeAdapter?
        get() = binding.rvReservationRequestTime.adapter as? ReservationRequestTimeAdapter
    private lateinit var reservationRequestTimeItemDeco: ReservationRequestTimeItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        getStoreId()
        getBundleData()
        observeIsGetReservationInfo()
        setPhoneNumberFormatTextWatcher()
        setTimeRecyclerAdapter()
        setTimeRecyclerItemDeco()
        setClickListener()
        checkSendBtnClickable()
        checkCheckBtnClickable()
        sendRequest()
        setCalenderDateClickListener()
        setPriceFormat()
    }


    private fun getStoreId() {
        val storeId = intent.getIntExtra(StoreDetailActivity.STORE_ID, 0)
        viewModel.updateStoreId(storeId)
    }

    private fun getBundleData() {
        val bundle = intent.getBundleExtra(SUITCASE_FEE)
        val suitCaseInfoList = bundle?.getParcelableArrayList<BaggageTypeInfo>(SUITCASE_FEE)

        if (suitCaseInfoList != null) {
            viewModel.updateSearchSuitCaseFee(suitCaseInfoList)
        } else {
            viewModel.updateSearchSuitCaseFee(emptyList())
        }
    }

    private fun setPhoneNumberFormatTextWatcher() {
        binding.etReservationRequestReservationPhoneNumber.addTextChangedListener(
            PhoneNumberFormattingTextWatcher()
        )
    }

    private fun setTimeRecyclerAdapter() {
        binding.rvReservationRequestTime.itemAnimator = null
        binding.rvReservationRequestTime.adapter = ReservationRequestTimeAdapter { pos, _ ->
            viewModel.itemClick(pos)
            reservationRequestTimeAdapter?.listChange(
                viewModel.prevStartTime.coerceAtMost(viewModel.startTime),
                viewModel.prevEndTime.coerceAtLeast(viewModel.endTime)
            )
            viewModel.checkIsSendBtnClickable()
            viewModel.getAmount()
        }
        submitListTimeRecyclerAdapter()
    }

    private fun setPriceFormat() {
        viewModel.amount.observe(this) {
            binding.tvReservationRequestReservationFee.setFormattedPrice(
                viewModel.amount.value ?: 0
            )
        }
    }

    private fun submitListTimeRecyclerAdapter() {
        viewModel.reservationRequestAvailableTimeList.observe(this) {
            viewModel.getReservationRequest()
            reservationRequestTimeAdapter?.submitList(viewModel.reservationRequestTimeList)
        }
    }

    private fun observeIsGetReservationInfo() {
        viewModel.isGetReservationInfo.observe(this) { isGetReservationInfo ->
            if (isGetReservationInfo) this.toast(getString(R.string.get_reservation_info_success))
        }
    }

    private fun setTimeRecyclerItemDeco() {
        reservationRequestTimeItemDeco = ReservationRequestTimeItemDecoration(baseContext)
        binding.rvReservationRequestTime.addItemDecoration(reservationRequestTimeItemDeco)
    }

    private fun sendRequest() {
        binding.btnReservationRequestSend.setOnClickListener {
            viewModel.postReservation()
            val intentToReservationDetail = Intent(this, MainActivity::class.java)
            intentToReservationDetail.flags = FLAG_ACTIVITY_CLEAR_TOP or FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intentToReservationDetail)
        }
    }

    private fun setClickListener() {
        setReservationCheckBtnClickListener()
    }

    private fun setReservationCheckBtnClickListener() {
        binding.btnReservationRequestInitialize.setOnClickListener {
            viewModel.clearSuitcase()
            binding.rvReservationRequestTime.adapter = null
            setTimeRecyclerAdapter()
            with(binding) {
                clReservationRequestReservation.visibility = View.GONE
                clReservationRequestPayment.visibility = View.GONE
            }
        }

        binding.btnReservationRequestCheck.setOnClickListener {
            viewModel.getReservationRequestTimeList()
            with(binding) {
                clReservationRequestReservation.visibility = View.VISIBLE
                clReservationRequestPayment.visibility = View.VISIBLE
            }
        }
    }

    private fun checkSendBtnClickable() {
        viewModel.name.observe(this) {
            viewModel.checkIsSendBtnClickable()
        }
        viewModel.phoneNumber.observe(this) {
            viewModel.checkIsSendBtnClickable()
        }
    }

    private fun checkCheckBtnClickable() {
        viewModel.suitCase.observe(this) {
            viewModel.checkIsCheckBtnClickable()
        }
    }

    private fun setCalenderDateClickListener() {
        binding.cvReservationRequest.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate =
                LocalDate.of(year, month + 1, dayOfMonth)
            viewModel.getFormattedDateString(selectedDate)
        }
    }
}
