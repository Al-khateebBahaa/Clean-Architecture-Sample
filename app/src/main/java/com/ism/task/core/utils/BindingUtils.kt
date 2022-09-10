package com.ism.task.core.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.Coil
import coil.load

object BindingUtils {

    @JvmStatic
    @BindingAdapter("downloadImage")
    fun downloadImage(view: ImageView, url: String) {

        view.load(url)


    }

}