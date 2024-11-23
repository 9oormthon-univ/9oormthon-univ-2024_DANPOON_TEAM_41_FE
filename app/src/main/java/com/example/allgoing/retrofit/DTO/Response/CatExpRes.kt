package com.example.allgoing.retrofit.DTO.Response

data class CatExpRes(
    val check: Boolean,
    val information: Information
) {
    data class Information(
        val level: Int,
        val catExp: Int
    )
}