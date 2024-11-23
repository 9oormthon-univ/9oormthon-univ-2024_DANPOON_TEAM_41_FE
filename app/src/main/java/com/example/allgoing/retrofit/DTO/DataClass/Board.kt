package com.example.allgoing.retrofit.DTO.DataClass

import com.google.gson.annotations.SerializedName

data class Board(
    @SerializedName("postId") val postId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("commentCount") val commentCount: Int,
    @SerializedName("isLiked") val isLiked: Boolean
)