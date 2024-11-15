package com.example.mvvmcoroutineretrofit.multiviewRV_FanCall_Practical

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmcoroutineretrofit.R
import com.example.mvvmcoroutineretrofit.databinding.ActivityFancallBinding

class FancallActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFancallBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFancallBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        populateData()
    }

    private fun populateData() {
        val mainList = ArrayList<ParentModel>()
        val bannerList = ArrayList<BannerModel>()
        val categoryList = ArrayList<CategoryModel>()
        val topSearchedList = ArrayList<TopSearchedModel>()

        bannerList.add(BannerModel(R.drawable.img_cake))
        bannerList.add(BannerModel(R.drawable.img_cake))
        bannerList.add(BannerModel(R.drawable.img_cake))
        bannerList.add(BannerModel(R.drawable.img_cake))

        categoryList.add(CategoryModel(R.drawable.img_koala))
        categoryList.add(CategoryModel(R.drawable.img_koala))
        categoryList.add(CategoryModel(R.drawable.img_koala))
        categoryList.add(CategoryModel(R.drawable.img_koala))

        topSearchedList.add(TopSearchedModel(R.drawable.img_youtuber2, "Ankit Patel", "Entertainment", "40K Subscribers", 3.5F))
        topSearchedList.add(TopSearchedModel(R.drawable.img_youtuber1, "Surabhi Shah", "Singer", "35K Subscribers", 4F))
        topSearchedList.add(TopSearchedModel(R.drawable.img_youtuber2, "Vishal Bhardvaj", "Entertainment", "50K Subscribers", 5F))
        topSearchedList.add(TopSearchedModel(R.drawable.img_youtuber1, "Neena Gupta", "Entertainment", "35K Subscribers", 3.5F))
        topSearchedList.add(TopSearchedModel(R.drawable.img_youtuber2, "Vishal Bhardvaj", "Entertainment", "50K Subscribers", 5F))

        mainList.add(ParentModel(1, null, bannerList, null, null))
        mainList.add(ParentModel(2, "Category", null, categoryList, null))
        mainList.add(ParentModel(3, "Top Searched Youtubers", null, null, topSearchedList))

        binding.rvMain.layoutManager = LinearLayoutManager(this)
        val mainAdapter = ParentAdapter(this, mainList)
        binding.rvMain.adapter = mainAdapter
    }
}