package com.example.allgoing.dataclass2

data class PostReply(
    val replyId : Int,
    val postId : Int,
    val memberId : Int,
    val replyContent : String,
    val createAt : String,
    val replyWriter : String
)
