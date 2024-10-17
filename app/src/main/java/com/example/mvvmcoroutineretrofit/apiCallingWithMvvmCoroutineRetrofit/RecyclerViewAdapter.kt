package com.example.mvvmcoroutineretrofit.apiCallingWithMvvmCoroutineRetrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcoroutineretrofit.R

class RecyclerViewAdapter(private val context: Context, var finalList: ArrayList<FinalModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvMfrName: TextView = itemView.findViewById(R.id.tvMfrName)
        fun bind(position: Int) {
            tvMfrName.text = finalList[position].mfrName
        }
    }
    private inner class View2ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvIsPrimary: TextView = itemView.findViewById(R.id.tvIsPrimary)
        var tvVehicleType: TextView = itemView.findViewById(R.id.tvVehicleType)
        fun bind(position: Int) {
            tvIsPrimary.text = finalList[position].isPrimary.toString()
            tvVehicleType.text = finalList[position].vehicleType
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return View1ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_view_1, parent, false)
            )
        }
        return View2ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_view_2, parent, false)
        )
    }
    override fun getItemCount(): Int {
        return finalList.size
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (finalList[position].viewType == 1) {
            (holder as View1ViewHolder).bind(position)
        } else {
            (holder as View2ViewHolder).bind(position)
        }
    }
    override fun getItemViewType(position: Int): Int {
        return finalList[position].viewType
    }
}