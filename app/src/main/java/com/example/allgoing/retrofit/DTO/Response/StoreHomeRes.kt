package com.example.allgoing.retrofit.DTO.Response

data class StoreHomeRes(
    val check: Boolean,
    val information: Information
) {
    data class Information(
        val storeId: Int,
        val storeName: String,
        val storeIntro: String,
        val storeAddress: String,
        val storePhone: String,
        val star: Double,
        val products: List<Product>,
        val storeImages: List<Any>,
        val storeInfos: List<StoreInfo>
    ) {
        data class Product(
            val productId: Int,
            val productName: String,
            val productPrice: Int,
            val productImageUrl: String
        )

        data class StoreInfo(
            val day: String,
            val openTime: String,
            val closeTime: String,
            val `open`: Boolean
        )
    }
}