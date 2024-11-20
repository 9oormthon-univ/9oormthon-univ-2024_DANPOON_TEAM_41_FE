package com.example.allgoing.dataclass

data class Community(
    var community_shop : String,
    var community_star : Float,
    var community_body : String,
    var community_likenum : Int,
    var community_commentnum : Int,
    var community_Img : Int? = null
)
