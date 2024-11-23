package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class BoardListRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: ArrayList<Information>
) {
    data class Information(
        @SerializedName("postId") val postId: Int,
        @SerializedName("title") val title: String,
        @SerializedName("content") val content: String,
        @SerializedName("createdAt") val createdAt: String,
        @SerializedName("thumbnailUrl") val thumbnailUrl: String,
        @SerializedName("likeCount") val likeCount: Int,
        @SerializedName("commentCount") val commentCount: Int,
        @SerializedName("isLiked") val isLiked: Boolean
    )
}