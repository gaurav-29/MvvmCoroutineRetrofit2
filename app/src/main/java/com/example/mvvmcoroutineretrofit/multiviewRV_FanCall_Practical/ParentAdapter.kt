package com.example.mvvmcoroutineretrofit.multiviewRV_FanCall_Practical

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcoroutineretrofit.databinding.ItemBannerBinding
import com.example.mvvmcoroutineretrofit.databinding.ItemCategoryBinding
import com.example.mvvmcoroutineretrofit.databinding.ItemTopSearchedBinding


class ParentAdapter(private val context: Context, private var mainList: ArrayList<ParentModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return BannerViewHolder(
                ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else if (viewType == 2) {
            return CategoryViewHolder(
                ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else if (viewType == 3) {
            return TopSearchedViewHolder(
                ItemTopSearchedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            return BannerViewHolder(
                ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }
    override fun getItemCount(): Int {
        return mainList.size
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (mainList[position].viewType == 1) {
            (holder as BannerViewHolder).bind(position)
        } else if (mainList[position].viewType == 2) {
            (holder as CategoryViewHolder).bind(position)
        } else if (mainList[position].viewType == 3) {
            (holder as TopSearchedViewHolder).bind(position)
        }
    }
    override fun getItemViewType(position: Int): Int {
        return mainList[position].viewType
    }
    private inner class BannerViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = mainList[position]

            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager.initialPrefetchItemCount = item.bannerList!!.size
            binding.rvBanner.layoutManager = layoutManager
            binding.rvBanner.adapter = item.bannerList?.let { ChildBannerAdapter(it) }
        }
    }
    private inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = mainList[position]
            binding.tvCategoryHeading.text = item.heading

            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager.initialPrefetchItemCount = item.categoryList!!.size
            binding.rvCategory.layoutManager = layoutManager

            binding.rvCategory.adapter = item.categoryList?.let { ChildCategoryAdapter(it) }
            binding.rvCategory.setRecycledViewPool(RecyclerView.RecycledViewPool())
        }
    }
    private inner class TopSearchedViewHolder(private val binding: ItemTopSearchedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = mainList[position]
            binding.tvTopSearchedHeading.text = item.heading

            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager.initialPrefetchItemCount = item.topSearchedList!!.size

            binding.rvTopSearched.layoutManager = layoutManager

            binding.rvTopSearched.adapter = item.topSearchedList?.let { ChildTopSearchedAdapter(it) }
            binding.rvTopSearched.setRecycledViewPool(RecyclerView.RecycledViewPool())

        }
    }
}