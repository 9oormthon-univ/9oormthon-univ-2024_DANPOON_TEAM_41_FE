package com.example.allgoing.retrofit.DTO.Request

data class ReviewCreateReq(
    val review: Review,
    val files: List<String>
) {
    data class Review(
        val reviewTitle: String,
        val reviewContent: String,
        val star: Int
    )
}