package com.example.allgoing.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allgoing.databinding.ItemDetailHomeMenuBinding
import com.example.allgoing.databinding.ItemHistoryOrderBinding
import com.example.allgoing.dataclass.DetailHomeMenu
import com.example.allgoing.dataclass.HistoryOrder
import okhttp3.internal.notifyAll

class DetailHomeMenuRVAdapter (var detailhomemenulist: ArrayList<DetailHomeMenu>) : RecyclerView.Adapter<DetailHomeMenuRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDetailHomeMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = detailhomemenulist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(detailhomemenulist[position])
    }

    inner class ViewHolder(private val binding: ItemDetailHomeMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detailHomeMenu: DetailHomeMenu) {
            binding.itemDetailHomeNameTv.text = detailHomeMenu.detail_home_item
            binding.itemDetailHomePriceTv.text = detailHomeMenu.detail_home_price
            if(detailHomeMenu.detail_home_menu_Img!=null){
                binding.itemDetailHomeFoodIv.setImageResource(detailHomeMenu.detail_home_menu_Img!!)
            }
        }
    }
}