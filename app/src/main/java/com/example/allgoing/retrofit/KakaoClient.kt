package com.example.allgoing.retrofit

import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager

class KakaoClient {

    companion object {
        private const val BASE_URL = "https://kapi.kakao.com/v2/" // url

        var builder = OkHttpClient().newBuilder()
        var okHttpClient = builder
            .cookieJar(JavaNetCookieJar(CookieManager()))
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: KakoService = retrofit.create(KakoService::class.java)
    }

}