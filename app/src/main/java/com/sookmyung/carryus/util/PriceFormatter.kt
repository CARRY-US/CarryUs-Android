package com.sookmyung.carryus.util

import java.text.DecimalFormat

class PriceFormatter {
    companion object {
        fun formatPrice(price: Int): String {
            return DecimalFormat("#,###").format(price)
        }
    }
}
