package com.example.mvvmcoroutineretrofit.apiCallingWithRetrofitCoroutine

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService2 {
    @GET("search")
    suspend fun getMusicList(@Query("term") term: String,
                             @Query("limit") limit: String): Response<MainModel>
}