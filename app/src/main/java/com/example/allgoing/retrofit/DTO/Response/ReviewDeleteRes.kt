package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class ReviewDeleteRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: String
)