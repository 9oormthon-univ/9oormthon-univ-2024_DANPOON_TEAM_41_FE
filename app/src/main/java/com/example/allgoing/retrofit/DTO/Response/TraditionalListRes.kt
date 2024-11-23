package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class TraditionalListRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: ArrayList<Information>
) {
    data class Information(
        @SerializedName("traditionalId") val traditionalId: Int,
        @SerializedName("traditionalName") val traditionalName: String,
        @SerializedName("traditionalLatitude") val traditionalLatitude: Double,
        @SerializedName("traditionalLongitude") val traditionalLongitude: Double
    )
}