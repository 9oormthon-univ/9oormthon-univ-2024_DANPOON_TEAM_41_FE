package com.example.allgoing.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allgoing.databinding.ItemSelectShopBinding

class SelectShopRVAdapter(): RecyclerView.Adapter<SelectShopRVAdapter.ViewHolder>() {

    var shop_list = arrayListOf("꼬양이네 생선가게", "꼬양이네 생선가게", "꼬양이네 생선가게")

    fun interface MyClickListener {
        fun itemSelect(shopName : String)
    }

    lateinit var myClickListener : MyClickListener

    fun setClickListener(clickListener: MyClickListener){
        myClickListener = clickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSelectShopBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener {
            myClickListener.itemSelect(shop_list[position])
        }
    }

    override fun getItemCount(): Int = shop_list.size

    inner class ViewHolder(val binding: ItemSelectShopBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            binding.itemSelectShopTv.text = shop_list[position]
        }
    }

}