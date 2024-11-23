package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class StoreReviewRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: ArrayList<Information>
) {
    data class Information(
        @SerializedName("reviewId") val reviewId: Int,
        @SerializedName("storeId") val storeId: Int,
        @SerializedName("userId") val userId: Int,
        @SerializedName("reviewTitle") val reviewTitle: String,
        @SerializedName("reviewContent") val reviewContent: String,
        @SerializedName("likeCount") val likeCount: Int,
        @SerializedName("writerName") val writerName: String,
        @SerializedName("commentCount") val commentCount: Int,
        @SerializedName("createdAt") val createdAt: String,
        @SerializedName("star") val star: Int,
        @SerializedName("reviewImages") val reviewImages: ArrayList<ReviewImage>
    ) {
        data class ReviewImage(
            @SerializedName("reviewImageId") val reviewImageId: Int,
            @SerializedName("reviewId") val reviewId: Int,
            @SerializedName("reviewImageUrl") val reviewImageUrl: String
        )
    }
}