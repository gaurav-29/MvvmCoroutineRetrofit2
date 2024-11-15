package com.example.mvvmcoroutineretrofit.multiviewRV_FanCall_Practical

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcoroutineretrofit.databinding.SubItemCategoryBinding

class ChildCategoryAdapter(private var dataList: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<ChildCategoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SubItemCategoryBinding.inflate(
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

    inner class MyViewHolder(private val binding: SubItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryModel) {
            binding.ivCategory.setImageResource(item.categoryImage)
        }
    }
}