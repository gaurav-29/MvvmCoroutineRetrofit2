package com.example.mvvmcoroutineretrofit.apiCallingWithMvvmCoroutineRetrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmcoroutineretrofit.R
import com.example.mvvmcoroutineretrofit.Utils
import com.example.mvvmcoroutineretrofit.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mfrsViewModel: MfrsViewModel
    private lateinit var resultList: ArrayList<Result>
    private lateinit var finalList: ArrayList<FinalModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        initObservers()
    }

    private fun initViews() {
        resultList = ArrayList()
        finalList = ArrayList()
        val retrofitService = RetrofitService.getInstance()
        val mfrsRepository = MfrsRepository(retrofitService)
        mfrsViewModel =
            ViewModelProvider(this, MfrsViewModelFactory(mfrsRepository))[MfrsViewModel::class.java]
    }

    private fun initObservers() {
        mfrsViewModel.mfrsResponse.observe(this) {
            Log.e("RES", "Response: ${it.body()}")
            resultList = it.body()?.results!!
            for (i in resultList) {
                finalList.add(FinalModel(i.mfrName, false, "", 1))
                if (i.vehicleTypes.isNotEmpty()) {
                    for (j in i.vehicleTypes) {
                        finalList.add(FinalModel("", j.isPrimary, j.name, 2))
                    }
                }
            }
            val mAdapter = RecyclerViewAdapter(this, finalList)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = mAdapter
            //adapter.setMovies(it)
        }

        mfrsViewModel.errorMessage.observe(this) {
            Log.e("RES_E", "Error: $it")
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        mfrsViewModel.isLoading.observe(this) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        if (Utils.isInternetAvailable(this)) {
            mfrsViewModel.getAllMfrsInViewModel("json")
        } else {
            Snackbar.make(binding.root, "Please check your internet connection.", Snackbar.LENGTH_LONG).show()
        }
    }
}