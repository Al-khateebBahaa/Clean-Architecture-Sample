package com.ism.task.di.modules

import android.content.Context
import com.google.gson.GsonBuilder
import com.ism.task.core.remote_api_config.NetworkResponseAdapterFactory
import com.ism.task.core.utils.Constants.ACCESS_KEY
import com.ism.task.core.utils.Constants.BASE_URL
import com.ism.task.core.utils.Constants.REQUEST_TIMEOUT
import com.ism.task.data.remote.AppApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


const val KEY = "Client-ID "

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Singleton
    @Provides
    fun provideRetrofitClient(): OkHttpClient {


        val networkInterceptor =
        Interceptor { chain: Interceptor.Chain ->

            val builder = chain.request()
                .newBuilder()
               .addHeader("Authorization", KEY.plus(ACCESS_KEY))
            chain.proceed(builder.build())
        }


        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(networkInterceptor)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }




    @Singleton
    @Provides
    fun provideRetrofitServices(
    okHttpClient: OkHttpClient,
        @ApplicationContext context: Context
    ): AppApiServices =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory((NetworkResponseAdapterFactory(context)))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClient)
            .build().create(AppApiServices::class.java)



}


