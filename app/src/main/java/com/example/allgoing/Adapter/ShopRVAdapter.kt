package com.example.allgoing.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allgoing.databinding.ItemShopBinding
import com.example.allgoing.dataclass.Shop

class ShopRVAdapter (var shoplist: ArrayList<Shop>) : RecyclerView.Adapter<ShopRVAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopRVAdapter.ViewHolder {
        val binding = ItemShopBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopRVAdapter.ViewHolder, position: Int) {
        holder.bind(shoplist[position])
    }

    override fun getItemCount(): Int =shoplist.size

    inner class ViewHolder(private val binding: ItemShopBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(shop: Shop){
            binding.itemShopItemNameTv.text = shop.shop_item_name
            if(shop.shop_item_Img!=null){
                binding.itemShopItemImgIv.setImageResource(shop.shop_item_Img!!)
            }
        }
    }

}