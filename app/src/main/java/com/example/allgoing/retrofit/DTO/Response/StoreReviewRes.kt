package com.example.allgoing.retrofit.DTO.Response

data class StoreReviewRes(
    val check: Boolean,
    val information: List<Information>
) {
    data class Information(
        val reviewId: Int,
        val storeId: Int,
        val userId: Int,
        val reviewTitle: String,
        val reviewContent: String,
        val likeCount: Int,
        val writerName: String,
        val commentCount: Int,
        val createdAt: String,
        val star: Int,
        val reviewImages: List<ReviewImage>
    ) {
        data class ReviewImage(
            val reviewImageId: Int,
            val reviewId: Int,
            val reviewImageUrl: String
        )
    }
}