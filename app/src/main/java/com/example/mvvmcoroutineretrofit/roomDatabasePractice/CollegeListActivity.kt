package com.example.mvvmcoroutineretrofit.roomDatabasePractice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.mvvmcoroutineretrofit.R
import com.example.mvvmcoroutineretrofit.apiCallingWithMvvmCoroutineRetrofit.FinalModel
import com.example.mvvmcoroutineretrofit.apiCallingWithMvvmCoroutineRetrofit.MfrsModel
import com.example.mvvmcoroutineretrofit.databinding.ActivityCollegeListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CollegeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCollegeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCollegeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        onClickListeners()
    }

    private fun onClickListeners() {


        binding.btnGetData.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                if (CollegeDatabase(this@CollegeListActivity).collegeDao().gelAllColleges().isNotEmpty()) {
                    Log.e("DATA", "CollegeList: "
                            + CollegeDatabase(this@CollegeListActivity).collegeDao().gelAllColleges())
                } else {
                    Log.e("DATA", "CollegeList: Empty")
                }
            }
        }
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddCollegeActivity::class.java))
        }
    }
}