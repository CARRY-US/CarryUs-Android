package com.sookmyung.carryus.util

import android.content.Context

fun Context.dpToPx(dp: Int): Int {
    val density = resources.displayMetrics.density
    return (dp * density).toInt()
}
