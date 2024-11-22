package com.example.allgoing.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allgoing.databinding.ItemOrderTimeBinding

class OrderTimeRVAdapter(time_list: ArrayList<String>): RecyclerView.Adapter<OrderTimeRVAdapter.ViewHolder>() {

    var time_list = time_list

    fun interface MyClickListener {
        fun itemSelect(posion:Int)
    }

    lateinit var myClickListener : MyClickListener

    fun setClickListener(clickListener: MyClickListener){
        myClickListener = clickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOrderTimeBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener {
            myClickListener.itemSelect(position)
        }
    }

    override fun getItemCount(): Int = time_list.size

    inner class ViewHolder(val binding: ItemOrderTimeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            binding.itemOrderTimeTime.text = time_list[position]
        }
    }


}