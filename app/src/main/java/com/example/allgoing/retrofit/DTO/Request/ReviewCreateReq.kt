package com.example.allgoing.retrofit.DTO.Request

import com.google.gson.annotations.SerializedName

data class ReviewCreateReq(
    @SerializedName("review") val review: Review,
    @SerializedName("files") val files: ArrayList<String>
) {
    data class Review(
        @SerializedName("reviewTitle") val reviewTitle: String,
        @SerializedName("reviewContent") val reviewContent: String,
        @SerializedName("star") val star: Int
    )
}