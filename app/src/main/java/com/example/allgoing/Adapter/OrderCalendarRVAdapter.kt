package com.example.allgoing.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allgoing.databinding.ItemOrderCalendarBinding

class OrderCalendarRVAdapter(date_list: ArrayList<String>): RecyclerView.Adapter<OrderCalendarRVAdapter.ViewHolder>() {

    var date_list = date_list

    fun interface MyClickListener {
        fun itemSelect(posion:Int)
    }

    lateinit var myClickListener : MyClickListener

    fun setClickListener(clickListener: MyClickListener){
        myClickListener = clickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOrderCalendarBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener {
            myClickListener.itemSelect(position)
        }
    }

    override fun getItemCount(): Int = date_list.size

    inner class ViewHolder(val binding: ItemOrderCalendarBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            binding.itemOrderCalendarDate.text = date_list[position].toString()
        }
    }

}