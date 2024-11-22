package com.example.allgoing.dataclass2

data class ReviewReply(
    val replyId : Int,
    val reviewId : Int,
    val memberId : Int,
    val replyContent : String,
    val createAt : String,
    val replyWriter : String
)
