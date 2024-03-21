package com.sookmyung.carryus.util.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.decode.SvgDecoder
import coil.load
import com.bumptech.glide.Glide
import com.sookmyung.carryus.util.PriceFormatter

object BindingAdapter {
    @BindingAdapter("setImage")
    @JvmStatic
    fun ImageView.setImage(imgUrl: String?) {
        this.let {
            Glide.with(context).load(imgUrl).into(this)
        }
    }

    @BindingAdapter("setCoilImage")
    @JvmStatic
    fun ImageView.setCoilImage(imgUrl: String?) {
        this.let {
            it.load(imgUrl) {
                if (imgUrl?.endsWith(".svg") == true) {
                    decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
                }
                crossfade(true)
            }
        }
    }

    @BindingAdapter("setFormattedPrice")
    @JvmStatic
    fun TextView.setFormattedPrice(amount: Int) {
        this.let {
            it.text = "${PriceFormatter.formatPrice(amount)}원"
        }
    }
}
