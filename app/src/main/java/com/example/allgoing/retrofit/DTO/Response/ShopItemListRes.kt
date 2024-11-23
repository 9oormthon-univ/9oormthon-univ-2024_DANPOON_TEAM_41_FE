package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class ShopItemListRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: Information
) {
    data class Information(
        @SerializedName("accessories") val accessories: ArrayList<Any>,
        @SerializedName("shoes") val shoes: ArrayList<Any>
    )
}