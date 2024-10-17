package com.example.mvvmcoroutineretrofit.apiCallingWithRetrofitCoroutine

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    const val BASE_URL = "https://itunes.apple.com/"

    fun makeRetrofitService(): RetrofitService2 {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService2::class.java)
    }
}