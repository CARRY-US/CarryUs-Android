package com.sookmyung.carryus.ui.reservationlist.detail

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sookmyung.carryus.R
import com.sookmyung.carryus.data.entitiy.request.CancelReservationRequest
import com.sookmyung.carryus.databinding.ActivityReservationDetailBinding
import com.sookmyung.carryus.databinding.ItemCustomCancelBottomsheetBinding
import com.sookmyung.carryus.ui.reservationlist.ReservationPagerFragment.Companion.RESERVATION_ID
import com.sookmyung.carryus.ui.search.storedetail.StoreDetailActivity
import com.sookmyung.carryus.util.binding.BindingActivity
import com.sookmyung.carryus.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationDetailActivity : BindingActivity<ActivityReservationDetailBinding>(R.layout.activity_reservation_detail) {
    private val viewModel: ReservationDetailViewModel by viewModels()
    private val bottomSheetViewModel: CancelBottomSheetViewModel by viewModels()
    private var cancelReason: String? = null
    private var reservationId: Int = 0

    private lateinit var bottomSheetBinding: ItemCustomCancelBottomsheetBinding
    private lateinit var  bottomSheetDialog: BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setContext()
        setReservationData()
        setCancelDialog()
        setGotoStoreDetail()
    }

    private fun setContext(){
        viewModel.setContext(this)
    }

    private fun setReservationData(){
        reservationId = intent.getIntExtra(RESERVATION_ID,0)

        viewModel.setReservationDetail(reservationId)
    }
    private fun setCancelDialog() {
        val customDialog = CustomDialog(this)
        val alertDialog = customDialog.create()

        customDialog.setTitle("정말로 취소하실건가요?")
        customDialog.setMessage("취소하시면 되돌릴 수 없어요.")
        customDialog.setPositiveButton("취소할게요", View.OnClickListener {
            alertDialog.dismiss()
            showBottomSheet()
        })

        customDialog.setNegativeButton("캐리할게요", View.OnClickListener {
            alertDialog.dismiss()
        })
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        viewModel.showDialog.observe(this) { showDialog ->
            if (showDialog) {
                alertDialog.show()
            } else {
                alertDialog.dismiss()
            }
        }
    }

    private fun showBottomSheet() {
        bottomSheetBinding = ItemCustomCancelBottomsheetBinding.inflate(layoutInflater)
        bottomSheetBinding.viewModel = bottomSheetViewModel
        bottomSheetDialog = BottomSheetDialog(this@ReservationDetailActivity)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        setBottomSheetViewModel()
        setBottomSheetBtnClickListener()

        bottomSheetDialog.show()
    }

    private fun setBottomSheetViewModel(){
        bottomSheetViewModel.setCancelReason(bottomSheetBinding.etCancelReason.text.toString())

        bottomSheetViewModel.cancelReason.observe(this) { text ->
            val isTextEmpty = text.isNullOrEmpty()
            bottomSheetBinding.btnCancelRequest.isEnabled = !isTextEmpty
            bottomSheetBinding.tvTextCount.text = "${text.length}/$MAXIMUM_LENGTH"
        }

        bottomSheetViewModel.cancelResultLiveData.observe(this) { isSuccess ->
            if (isSuccess) {
                bottomSheetDialog.dismiss()
                viewModel.setReservationDetail(reservationId)
                applicationContext.toast("취소 성공")
            } else {
                applicationContext.toast("취소 실패")
            }
        }
    }

    private fun setBottomSheetBtnClickListener(){
        with(bottomSheetBinding){
            btnClose.setOnClickListener {
                bottomSheetDialog.dismiss()
            }

            btnCancelRequest.setOnClickListener {
                cancelReason = etCancelReason.text.toString()
                bottomSheetViewModel.postCancelReservation(CancelReservationRequest(reservationId = reservationId, cancelReason = cancelReason ?: ""))
            }
        }
    }

    private fun setGotoStoreDetail(){
        binding.clGoToShopDetail.setOnClickListener {
            val intent = Intent(this, StoreDetailActivity::class.java)
            intent.putExtra(StoreDetailActivity.STORE_ID, viewModel.reservationDetailLiveData.value?.storeId)
            startActivity(intent)
        }
    }

    companion object{
        const val MAXIMUM_LENGTH = 1000
    }

}

