package com.example.mvvmcoroutineretrofit.apiCallingWithMvvmCoroutineRetrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MfrsViewModelFactory (private val repository: MfrsRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MfrsViewModel::class.java)) {
            MfrsViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}