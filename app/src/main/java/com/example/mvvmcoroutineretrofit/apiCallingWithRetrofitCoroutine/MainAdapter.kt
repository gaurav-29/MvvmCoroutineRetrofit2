package com.example.mvvmcoroutineretrofit.apiCallingWithRetrofitCoroutine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcoroutineretrofit.R
import com.example.mvvmcoroutineretrofit.databinding.ItemMainBinding

class MainAdapter(private var dataList:List<Result>,
                  private val onClickInterface: GenericOnClickInterface<Result>):
    RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun filterList(filterList: ArrayList<Result>) {
        dataList = filterList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result) {
            binding.tvTrackName.text = item.trackName
            binding.tvArtistName.text = item.artistName

            if (item.isSelected) {
                binding.ivFavorite.setImageResource(R.drawable.ic_fav_selected)
            } else {
                binding.ivFavorite.setImageResource(R.drawable.ic_fav_unselected)
            }

            binding.ivFavorite.setOnClickListener {
                onClickInterface.onItemClick(adapterPosition, item)
            }
        }
    }
}