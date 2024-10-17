package com.example.mvvmcoroutineretrofit.roomDatabasePractice

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.mvvmcoroutineretrofit.R
import com.example.mvvmcoroutineretrofit.databinding.ActivityAddCollegeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class AddCollegeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCollegeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        binding = ActivityAddCollegeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        onClickListeners()
    }

    private fun onClickListeners() {
        binding.btnAddCollege.setOnClickListener {

            if (checkValidation()) {
                val collegeName = binding.etCollegeName.text.trim().toString()
                val degreeName = binding.etDegreeName.text.trim().toString()
                val city = binding.etCity.text.trim().toString()

                val college = CollegeEntity(null, collegeName, degreeName, city)

                lifecycleScope.launch(Dispatchers.IO) {
                    CollegeDatabase(this@AddCollegeActivity).collegeDao().insertCollege(college)
                }
            }
        }
    }
    private fun checkValidation(): Boolean {
        val collegeName = binding.etCollegeName.text.trim().toString()
        val degreeName = binding.etDegreeName.text.trim().toString()
        val city = binding.etCity.text.trim().toString()

        if (collegeName.isEmpty()) {
            binding.etCollegeName.error = "This field is required"
            return false
        }
        if (degreeName.isEmpty()) {
            binding.etDegreeName.error = "This field is required"
            return false
        }
        if (city.isEmpty()) {
            binding.etCity.error = "This field is required"
            return false
        }
        return true
    }
}