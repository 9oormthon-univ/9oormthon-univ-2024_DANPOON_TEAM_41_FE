package com.example.allgoing.retrofit.DTO.DataClass

import com.google.gson.annotations.SerializedName

data class Accessory(
    @SerializedName("itemId") val itemId: Int,
    @SerializedName("itemName") val itemName: String,
    @SerializedName("itemPrice") val itemPrice: Int,
    @SerializedName("isOwned") val isOwned: Boolean,
    @SerializedName("isEquipped") val isEquipped: Boolean
)