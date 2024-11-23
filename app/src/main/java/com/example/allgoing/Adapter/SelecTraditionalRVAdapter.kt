package com.example.allgoing.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allgoing.databinding.ItemSelectShopBinding
import com.example.allgoing.databinding.ItemSelectTraditionalBinding

class SelecTraditionalRVAdapter(): RecyclerView.Adapter<SelecTraditionalRVAdapter.ViewHolder>() {

    var tradtitional_list = arrayListOf("중앙시장", "역전시장", "제일시장")

    fun interface MyClickListener {
        fun itemSelect(traditionalName : String)
    }

    lateinit var myClickListener : MyClickListener

    fun setClickListener(clickListener: MyClickListener){
        myClickListener = clickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSelectTraditionalBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener {
            myClickListener.itemSelect(tradtitional_list[position])
        }
    }

    override fun getItemCount(): Int = tradtitional_list.size

    inner class ViewHolder(val binding: ItemSelectTraditionalBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            binding.itemSelectTraditionalTv.text = tradtitional_list[position]
        }
    }

}