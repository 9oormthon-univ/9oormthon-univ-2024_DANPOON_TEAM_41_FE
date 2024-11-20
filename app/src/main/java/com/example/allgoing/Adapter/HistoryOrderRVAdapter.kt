package com.example.allgoing.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allgoing.databinding.ItemHistoryOrderBinding
import com.example.allgoing.dataclass.HistoryOrder

class HistoryOrderRVAdapter (var historyorderlist: ArrayList<HistoryOrder>) : RecyclerView.Adapter<HistoryOrderRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = historyorderlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(historyorderlist[position])
    }

    inner class ViewHolder(private val binding: ItemHistoryOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(historyOrder: HistoryOrder) {
            binding.itemHistoryOrderNameTv.text = historyOrder.historyorder_shop
            binding.itemHistoryOrderDateTv.text = historyOrder.historyorder_date
            binding.itemHistoryOrderItemTv.text = historyOrder.historyorder_body
        }
    }
}