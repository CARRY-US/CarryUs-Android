package com.sookmyung.carryus.ui.reservationlist

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ActivityReservationDetailBinding
import com.sookmyung.carryus.databinding.ItemCustomCancelBottomsheetBinding
import com.sookmyung.carryus.domain.entity.ReservationDetailResponse
import com.sookmyung.carryus.util.binding.BindingActivity

class ReservationDetailActivity : BindingActivity<ActivityReservationDetailBinding>(R.layout.activity_reservation_detail) {
    private val viewModel: ReservationDetailViewModel by viewModels()

    private var cancelReason: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setContext()
        setReservationData()
        setCancelDialog()
    }

    private fun setContext(){
        viewModel.setContext(this)
    }

    private fun setReservationData(){
        val reservationId = intent.getIntExtra("reservation_id",0)
        Log.d("ReservationDetailActivity","$reservationId")


        // 데이터 설정
        viewModel.setReservationDetail(
            ReservationDetailResponse(1,1,"URL","가게 이름","대기중"
                ,"2024.02.10 14:00","24인치 1개, 20인치 3개","장나리","010-0000-0000","살살 다뤄주세요.",20000)
        )

    }
    private fun setCancelDialog() {
        val customDialog = CustomCancelDialog(this)
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
        val bottomSheetBinding = ItemCustomCancelBottomsheetBinding.inflate(layoutInflater)

        // 바텀시트 다이얼로그 생성
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        bottomSheetBinding.btnClose.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetBinding.btnCancelRequest.setOnClickListener {
            cancelReason = bottomSheetBinding.etCancelReason.text.toString()
            Log.d("CustomCancelBottomSheetFragment","$cancelReason")
            Toast.makeText(this, "예약이 취소되었어요", Toast.LENGTH_SHORT).show()
            bottomSheetDialog.dismiss()
        }
        // 바텀시트 표시
        bottomSheetDialog.show()
    }

}

