package com.example.mvvmcoroutineretrofit.apiCallingWithMvvmCoroutineRetrofit

class MfrsRepository(private val retrofitService: RetrofitService) {

    suspend fun getAllMfrsInRepo(format:String) = retrofitService.getAllManufacturers(format)
}