package com.example.allgoing.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allgoing.databinding.ItemShopBinding
import com.example.allgoing.dataclass.Shop

class ShopRVAdapter (var shoplist: ArrayList<Shop>) : RecyclerView.Adapter<ShopRVAdapter.ViewHolder>(){
    fun interface MyClickListener {
        fun itemSelect(id:String)
    }

    lateinit var myClickListener : MyClickListener

    fun setClickListener(clickListener: MyClickListener){
        myClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopRVAdapter.ViewHolder {
        val binding = ItemShopBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopRVAdapter.ViewHolder, position: Int) {
        holder.bind(shoplist[position])
        holder.itemView.setOnClickListener {
            myClickListener.itemSelect(shoplist[position].id)
        }
    }

    override fun getItemCount(): Int =shoplist.size

    inner class ViewHolder(private val binding: ItemShopBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(shop: Shop){
            binding.itemShopItemNameTv.text = shop.shop_item_name
            binding.itemShopItemImgIv.setImageResource(shop.shop_item_Img!!)
        }
    }

}