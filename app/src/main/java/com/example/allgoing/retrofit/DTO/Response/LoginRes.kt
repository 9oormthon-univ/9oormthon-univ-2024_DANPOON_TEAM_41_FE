package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class LoginRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: Information
) {
    data class Information(
        @SerializedName("accessToken") val accessToken: String,
        @SerializedName("refreshToken") val refreshToken: String
    )
}