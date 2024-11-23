package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class StoreHomeRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: Information
) {
    data class Information(
        @SerializedName("storeId") val storeId: Int,
        @SerializedName("storeName") val storeName: String,
        @SerializedName("storeIntro") val storeIntro: String,
        @SerializedName("storeAddress") val storeAddress: String,
        @SerializedName("storePhone") val storePhone: String,
        @SerializedName("star") val star: Double,
        @SerializedName("products") val products: ArrayList<Product>,
        @SerializedName("storeImages") val storeImages: ArrayList<a>,
        @SerializedName("storeInfos") val storeInfos: ArrayList<StoreInfo>
    ) {
        data class Product(
            @SerializedName("productId") val productId: Int,
            @SerializedName("productName") val productName: String,
            @SerializedName("productPrice") val productPrice: Int,
            @SerializedName("productImageUrl") val productImageUrl: String
        )

        data class StoreInfo(
            @SerializedName("day") val day: String,
            @SerializedName("openTime") val openTime: String,
            @SerializedName("closeTime") val closeTime: String,
            @SerializedName("open") val `open`: Boolean
        )

        data class a(
            @SerializedName("storeImageUrl") val storeImageUrl : String,
            @SerializedName("storeImageType") val storeImageType : String
        )
    }
}