package com.sookmyung.carryus.ui.reservationlist.detail

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.sookmyung.carryus.R
import com.sookmyung.carryus.databinding.ItemCustomCancelDialogBinding

class CancelDialog(private val context: Context) {

    private val binding: ItemCustomCancelDialogBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.item_custom_cancel_dialog,
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


