package com.example.allgoing.retrofit

import com.example.allgoing.retrofit.DTO.Response.kakaoUserRes
import retrofit2.Call
import retrofit2.http.*

interface KakoService {

    @GET("user/me")
    fun getUser(
        @Header("authorization") Authorization : String,
//        @Header("content-Type") type : String = "application/x-www-form-urlencoded;charset=utf-8",
        @Query("property_keys") property : String = "[\"kakao_account.profile\",\"kakao_account.email\"]"
    ):Call<kakaoUserRes>

}