package com.example.allgoing.retrofit.DTO.Response

data class ReviewDetailRes(
    val check: Boolean,
    val information: Information
) {
    data class Information(
        val reviewId: Int,
        val reviewTitle: String,
        val reviewContent: String,
        val likeCount: Int,
        val writerName: String,
        val reviewImages: List<ReviewImage>,
        val liked: Boolean,
        val createdAt: String,
        val star: Int,
        val userId: Int,
        val storeId: Int
    ) {
        data class ReviewImage(
            val reviewImageId: Int,
            val reviewId: Int,
            val reviewImageUrl: String
        )
    }
}