package com.example.allgoing.retrofit

import com.example.allgoing.retrofit.DTO.Request.LoginReq
import com.example.allgoing.retrofit.DTO.Response.LoginRes
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @POST("/auth/idTokenLogin")
    fun postLogin(
        @Body request: LoginReq
    ): Call<LoginRes>

}