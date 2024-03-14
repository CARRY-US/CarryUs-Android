package com.sookmyung.carryus.ui.reservationlist

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivityReservationDetailBinding
import com.sookmyung.carryus.databinding.ItemCustomCancelDialogBinding
import com.sookmyung.carryus.util.binding.BindingActivity

class ReservationDetailActivity : BindingActivity<ActivityReservationDetailBinding>(R.layout.activity_reservation_detail) {
    private val viewModel: ReservationDetailViewModel by viewModels()
    private lateinit var customDialog: CustomCancelDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel()
        setCancelDialog()
    }

    private fun setCancelDialog() {
        val customDialog = CustomCancelDialog(this)
        val alertDialog = customDialog.create()

        customDialog.setTitle("정말로 취소하실건가요?")
        customDialog.setMessage("취소하시면 되돌릴 수 없어요.")
        customDialog.setPositiveButton("취소할게요", View.OnClickListener {

            alertDialog.dismiss()
        })

        customDialog.setNegativeButton("캐리할게", View.OnClickListener {
            // 취소버튼 누르면 대화상자 종료
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

    private fun setViewModel() {
        binding.viewModel = viewModel
    }
}

