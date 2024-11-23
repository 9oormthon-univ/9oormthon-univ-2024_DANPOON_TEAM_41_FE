package com.example.allgoing.retrofit.DTO.Request

import com.google.gson.annotations.SerializedName

data class PostNewReq(
    @SerializedName("newPostRequest") val newPostRequest: NewPostRequest,
    @SerializedName("files") val files: ArrayList<String>
) {
    data class NewPostRequest(
        @SerializedName("title") val title: String,
        @SerializedName("content") val content: String
    )
}