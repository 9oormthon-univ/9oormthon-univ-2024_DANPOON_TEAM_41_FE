package com.example.allgoing.retrofit.DTO.Response

import com.example.allgoing.retrofit.DTO.DataClass.Accessory
import com.example.allgoing.retrofit.DTO.DataClass.Shoe
import com.google.gson.annotations.SerializedName

data class ShopItemListRes(
    @SerializedName("accessories") val accessories: ArrayList<Accessory>,
    @SerializedName("shoes") val shoes: ArrayList<Shoe>
)