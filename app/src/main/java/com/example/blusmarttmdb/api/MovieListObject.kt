package com.example.blusmarttmdb.api

import android.util.Log
import com.example.blusmarttmdb.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieListObject {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val OKHTTP = "okhttp"
    var okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request: Request = chain.request()
            if (BuildConfig.DEBUG) {
                Log.d(OKHTTP, request.method().toString() + " " + request.url())
                Log.d(OKHTTP, "" + request.header("Cookie"))
                val buffer = Buffer()
                request.body()?.writeTo(buffer)
                Log.d(OKHTTP, "Payload- " + buffer.readUtf8())
            }
            chain.proceed(request)
        }
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}