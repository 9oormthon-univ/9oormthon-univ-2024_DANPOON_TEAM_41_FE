package com.example.allgoing.retrofit.DTO.DataClass

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("reviewId") val reviewId: Int,
    @SerializedName("reviewTitle") val reviewTitle: String,
    @SerializedName("reviewContent") val reviewContent: String,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("writerName") val writerName: String,
    @SerializedName("reviewImages") val reviewImages: ArrayList<ReviewImg>,
    @SerializedName("liked") val liked: Boolean,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("star") val star: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("storeId") val storeId: Int
)