package com.example.mvvmcoroutineretrofit.multiviewRV_FanCall_Practical

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcoroutineretrofit.databinding.SubItemCategoryBinding
import com.example.mvvmcoroutineretrofit.databinding.SubItemTopSearchedBinding

class ChildTopSearchedAdapter(private var dataList: ArrayList<TopSearchedModel>) :
    RecyclerView.Adapter<ChildTopSearchedAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = SubItemTopSearchedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return MyViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MyViewHolder(private val binding: SubItemTopSearchedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TopSearchedModel) {
            binding.ivTopSearched.setImageResource(item.topSearchedImage)
            binding.tvName.text = item.name
            binding.tvType.text = item.type
            binding.tvSubscribers.text = item.subscribers
            binding.rating.rating = item.rating
        }
    }
}