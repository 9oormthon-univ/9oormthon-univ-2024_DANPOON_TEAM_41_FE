package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class ShopItemListRes(
    @SerializedName("accessories") val accessories: ArrayList<Accessory>,
    @SerializedName("shoes") val shoes: ArrayList<Shoe>
) {
    data class Accessory(
        @SerializedName("itemId") val itemId: Int,
        @SerializedName("itemName") val itemName: String,
        @SerializedName("itemPrice") val itemPrice: Int,
        @SerializedName("isOwned") val isOwned: Boolean,
        @SerializedName("isEquipped") val isEquipped: Boolean
    )

    data class Shoe(
        @SerializedName("itemId") val itemId: Int,
        @SerializedName("itemName") val itemName: String,
        @SerializedName("itemPrice") val itemPrice: Int,
        @SerializedName("isOwned") val isOwned: Boolean,
        @SerializedName("isEquipped") val isEquipped: Boolean
    )
}