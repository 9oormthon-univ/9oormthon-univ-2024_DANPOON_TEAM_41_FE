package com.example.allgoing.retrofit.DTO.Response

data class StoreAllListRes(
    val check: Boolean,
    val information: List<Information>
) {
    data class Information(
        val storeId: Int,
        val storeName: String,
        val storeIntro: String,
        val storeAddress: String,
        val star: Double,
        val storeLatitude: Double,
        val storeLongitude: Double
    )
}