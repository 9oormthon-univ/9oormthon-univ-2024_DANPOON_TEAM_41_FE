package com.example.allgoing.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allgoing.databinding.ItemDetailHomeImgBinding
import com.example.allgoing.databinding.ItemHistoryOrderBinding
import com.example.allgoing.dataclass.DetailHomeImg
import com.example.allgoing.dataclass.HistoryOrder

class DetailHomeImgRVAdapter (var detailHomeImglist: ArrayList<DetailHomeImg>) : RecyclerView.Adapter<DetailHomeImgRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDetailHomeImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = detailHomeImglist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(detailHomeImglist[position])
    }

    inner class ViewHolder(private val binding: ItemDetailHomeImgBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detailHomeImg: DetailHomeImg) {
            if (detailHomeImg.detial_home_Img != null) {
                binding.itemDetailHomeFoodIv.setImageResource(detailHomeImg.detial_home_Img!!)
                binding.itemDetailHomeFoodIv.visibility = View.VISIBLE
            } else {
                binding.itemDetailHomeFoodIv.visibility = View.GONE
            }
        }
    }
}