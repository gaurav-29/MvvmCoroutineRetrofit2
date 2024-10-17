package com.example.mvvmcoroutineretrofit.apiCallingWithRetrofitCoroutine

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmcoroutineretrofit.Utils
import com.example.mvvmcoroutineretrofit.databinding.ActivityMainRetrofitCoroutineBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityRetrofitCoroutine : AppCompatActivity(), GenericOnClickInterface<Result> {
    private lateinit var binding: ActivityMainRetrofitCoroutineBinding
    private lateinit var dataList: List<Result>
    private lateinit var favoriteList: ArrayList<Result>
    private lateinit var mAdapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRetrofitCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sample Text
        initViews()
        initSearchView()
    }

    private fun initViews() {
        mAdapter = MainAdapter(ArrayList(), this)
        favoriteList = arrayListOf()

        if (Utils.isInternetAvailable(this@MainActivityRetrofitCoroutine)) {
            callAPI()
        } else {
            Toast.makeText(this@MainActivityRetrofitCoroutine, "No Internet Connection", Toast.LENGTH_LONG).show()
        }
    }

    private fun initSearchView() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filterData(p0.toString())
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun filterData(text: String) {
        val filteredList: ArrayList<Result> = ArrayList()
        for (item in dataList) {
            // Search by track name or artist name
            if (item.trackName.lowercase().contains(text.trim().lowercase()) ||
                item.artistName.lowercase().contains(text.trim().lowercase())) {
                filteredList.add(item)
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this@MainActivityRetrofitCoroutine, "No data found", Toast.LENGTH_SHORT).show()
        } else {
            mAdapter.filterList(filteredList)
        }
    }

    private fun callAPI() {
        binding.progressBar.visibility = View.VISIBLE
        val service = RetrofitFactory.makeRetrofitService()

        CoroutineScope(Dispatchers.IO).launch {

            val response = service.getMusicList("jack johnson", "30")

            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        Log.e("RES", response.body().toString())
                        Log.e(
                            "RES_SORTED",
                            response.body()?.results?.sortedBy { it.trackName }.toString()
                        )
                        binding.progressBar.visibility = View.GONE
                        dataList = response.body()?.results!!
                        if (dataList.isNotEmpty()) {

                            setRecyclerView(dataList)
                            //mAdapter.notifyDataSetChanged()
                        } else {
                            Toast.makeText(
                                this@MainActivityRetrofitCoroutine,
                                "No data found",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@MainActivityRetrofitCoroutine,
                            "Error: ${response.errorBody()}",
                            Toast.LENGTH_LONG
                        ).show()
                        binding.progressBar.visibility = View.GONE
                    }
                } catch (e: retrofit2.HttpException) {
                    Toast.makeText(
                        this@MainActivityRetrofitCoroutine,
                        "Exception ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.progressBar.visibility = View.GONE
                } catch (e: Throwable) {
                    Toast.makeText(
                        this@MainActivityRetrofitCoroutine,
                        "Oops, something went wrong...",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun setRecyclerView(dataList: List<Result>) {
        binding.rvMain.layoutManager = LinearLayoutManager(this@MainActivityRetrofitCoroutine)
        mAdapter = MainAdapter(dataList, this)
        binding.rvMain.adapter = mAdapter
    }

    override fun onItemClick(position: Int, item: Result) {
        dataList[position].isSelected = !dataList[position].isSelected
        mAdapter.notifyDataSetChanged()

        if (dataList[position].isSelected) {
            favoriteList.add(dataList[position])
        } else {
            favoriteList.remove(dataList[position])
        }
        for (i in favoriteList)
        {
            Log.e("FAV_LIST", i.artistName)
        }
    }
}