package com.example.mvvmcoroutineretrofit.apiCallingWithMvvmCoroutineRetrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response


class MfrsViewModel(private val mfrsRepository: MfrsRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val mfrsResponse = MutableLiveData<Response<MfrsModel>>()
    private var job: Job? = null
    val isLoading = MutableLiveData<Boolean>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getAllMfrsInViewModel(format: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mfrsRepository.getAllMfrsInRepo(format)
            withContext(Dispatchers.Main + exceptionHandler) {
                mfrsResponse.value = response
                isLoading.value = false
            }
        }
    }
    private fun onError(message: String) {
        errorMessage.value = message
        isLoading.value = false
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}