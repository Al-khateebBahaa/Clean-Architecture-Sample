package com.ism.task.di

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.ism.task.R
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AppApplication : Application(), ImageLoaderFactory {


    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true).placeholder(R.drawable.no_image)
            .build()
    }

}




