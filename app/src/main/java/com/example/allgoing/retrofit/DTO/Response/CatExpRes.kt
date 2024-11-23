package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class CatExpRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: Information
) {
    data class Information(
        @SerializedName("level") val level: Int,
        @SerializedName("catExp") val catExp: Int
    )
}