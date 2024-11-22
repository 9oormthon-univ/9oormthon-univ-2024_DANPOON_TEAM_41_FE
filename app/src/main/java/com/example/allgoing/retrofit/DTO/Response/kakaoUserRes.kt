package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class kakaoUserRes(
    @SerializedName("Content-Type") val Content : String

){
    data class Result(
        val properties : Properties,
        val kakao_account : KakaoAccount
    )

    data class Properties(
        val nickname : String,
        val thumbnail_image_url : String
    )

    data class KakaoAccount(
        val email : String
    )
}

