package com.jps.travelbankexercise.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object Networking {
    private const val NETWORK_CALL_TIMEOUT = 60

    fun create( baseUrl: String, cacheDir: File, cacheSize: Long): NetworkService {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(
                        OkHttpClient.Builder()
                                .cache(Cache(cacheDir, cacheSize))
                                .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                                .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                                .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NetworkService::class.java)
    }
}