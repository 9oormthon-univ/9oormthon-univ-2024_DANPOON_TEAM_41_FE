package com.example.allgoing.retrofit.DTO.Response

data class StoreAllListRes(
    val check: Boolean,
    val information: List<Information>
) {
    data class Information(
        val traditionalId: Int,
        val traditionalName: String,
        val traditionalLatitude: Int,
        val traditionalLongitude: Int
    )
}