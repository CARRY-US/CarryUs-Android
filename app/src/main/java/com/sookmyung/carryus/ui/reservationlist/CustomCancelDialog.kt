package com.sookmyung.carryus.ui.reservationlist

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ItemCustomCancelDialogBinding

class CustomCancelDialog(private val context: Context) {

    private val binding: ItemCustomCancelDialogBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.item_custom_cancel_dialog, // 레이아웃 파일명을 사용자의 실제 레이아웃에 맞게 수정
        null,
        false
    )

    fun create(): Dialog {
        val dialog = Dialog(context)
        dialog.setContentView(binding.root)
        return dialog
    }

    fun setTitle(title: String) {
        binding.tvDialogTitle.text = title
    }

    fun setMessage(message: String) {
        binding.tvDialogMessage.text = message
    }

    fun setPositiveButton(text: String, listener: View.OnClickListener) {
        binding.tvCancelAction.text = text
        binding.tvCancelAction.setOnClickListener(listener)
    }

    fun setNegativeButton(text: String, listener: View.OnClickListener) {
        binding.tvCarryAction.text = text
        binding.tvCarryAction.setOnClickListener(listener)
    }
}


