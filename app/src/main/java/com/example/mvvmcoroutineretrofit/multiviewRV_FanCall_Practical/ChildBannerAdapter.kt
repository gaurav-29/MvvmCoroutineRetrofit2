package com.example.mvvmcoroutineretrofit.multiviewRV_FanCall_Practical

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcoroutineretrofit.databinding.SubItemBannerBinding

class ChildBannerAdapter(private var dataList: ArrayList<BannerModel>) :
    RecyclerView.Adapter<ChildBannerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SubItemBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MyViewHolder(private val binding: SubItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BannerModel) {
            binding.ivBanner.setImageResource(item.bannerImage)
        }
    }
}