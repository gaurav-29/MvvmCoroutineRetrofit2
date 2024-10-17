package com.example.mvvmcoroutineretrofit.apiCallingWithRetrofitCoroutine

data class MainModel(
    var resultCount: Int,
    var results: List<Result>
)

data class Result(
    var trackId: Int,
    var artistName: String,
    var trackName: String,
    var isSelected: Boolean = false
)