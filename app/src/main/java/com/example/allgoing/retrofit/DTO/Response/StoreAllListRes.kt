package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class StoreAllListRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: List<Information>
) {
    data class Information(
        @SerializedName("traditionalId") val traditionalId: Int,
        @SerializedName("traditionalName") val traditionalName: String,
        @SerializedName("traditionalLatitude") val traditionalLatitude: Int,
        @SerializedName("traditionalLongitude") val traditionalLongitude: Int
    )
}