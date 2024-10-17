package com.example.mvvmcoroutineretrofit.apiCallingWithRetrofitCoroutine

interface GenericOnClickInterface<T> {
    fun onItemClick(position: Int, item: T)
}