package com.example.mvvmcoroutineretrofit.apiCallingWithMvvmCoroutineRetrofit

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET ("api/vehicles/getallmanufacturers")
    suspend fun getAllManufacturers(@Query ("format") format: String): Response<MfrsModel>

    companion object {
        private var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {  // To ensure that only one instance(Singleton Object) of Retrofit is created.
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://vpic.nhtsa.dot.gov/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}