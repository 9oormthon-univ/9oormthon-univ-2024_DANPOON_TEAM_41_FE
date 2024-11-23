package com.example.allgoing.retrofit.DTO.DataClass

import com.google.gson.annotations.SerializedName

data class ReviewImg(
    @SerializedName("reviewImageId") val reviewImageId: Int,
    @SerializedName("reviewId") val reviewId: Int,
    @SerializedName("reviewImageUrl") val reviewImageUrl: String
)