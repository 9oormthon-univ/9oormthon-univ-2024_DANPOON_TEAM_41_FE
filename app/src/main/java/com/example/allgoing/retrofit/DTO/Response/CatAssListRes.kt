package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class CatAssListRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: Information
) {
    data class Information(
        @SerializedName("coin") val coin: Int,
        @SerializedName("catItems") val catItems: ArrayList<CatItem>
    ) {
        data class CatItem(
            @SerializedName("itemId") val itemId: Int,
            @SerializedName("itemCategory") val itemCategory: String,
            @SerializedName("itemName") val itemName: String,
            @SerializedName("itemPrice") val itemPrice: Int
        )
    }
}