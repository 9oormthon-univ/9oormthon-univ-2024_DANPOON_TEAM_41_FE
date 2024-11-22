package com.example.allgoing.dataclass2

data class Review(
    val reviewId : Int,
    val storeId : Int,
    val memberId : Int,
    val reviewTitle : String,
    val reviewContent : String,
    val reviewPic : String,
    val likeCount : Int,
    val createAt : String,
    val reviewWriter : String
)
