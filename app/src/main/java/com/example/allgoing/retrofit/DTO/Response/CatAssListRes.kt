package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class CatAssListRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: Information
) {
    data class Information(
        @SerializedName("coin") val coin: Int,
        @SerializedName("catItems") val catItems: ArrayList<Any>
    )
}