package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class ShopCoinRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: Information
) {
    data class Information(
        @SerializedName("coin") val coin: Int
    )
}