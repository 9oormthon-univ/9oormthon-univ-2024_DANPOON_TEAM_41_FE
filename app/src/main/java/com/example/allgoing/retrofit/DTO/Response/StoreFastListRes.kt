package com.example.allgoing.retrofit.DTO.Response

data class StoreFastListRes(
    val check: Boolean,
    val information: Information
) {
    data class Information(
        val storeId: Int,
        val storeName: String,
        val storeIntro: String,
        val storeAddress: String,
        val storeImageUrl: String,
        val star: Double,
        val storeInfos: List<StoreInfo>,
        val reviewCount: Int
    ) {
        data class StoreInfo(
            val day: String,
            val openTime: String,
            val closeTime: String,
            val `open`: Boolean
        )
    }
}