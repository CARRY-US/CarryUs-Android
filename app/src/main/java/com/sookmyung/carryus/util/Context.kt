package com.sookmyung.carryus.util

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.hideKeyboard(view: View?) {
    if (view is EditText) {
        view.clearFocus()
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun snackBar(
    anchorView: View,
    message: String
) {
    Snackbar.make(anchorView, message, Snackbar.LENGTH_SHORT).show()
}

fun Context.toast(message: String) {
    val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast.show()
    Handler(Looper.getMainLooper()).postDelayed({
        run() { toast.cancel() }
    }, 1000)
}

fun View.setSingleOnClickListener(onSingleClick: (View) -> Unit) {
    setOnClickListener(OnSingleClickListener { onSingleClick(it) })
}
