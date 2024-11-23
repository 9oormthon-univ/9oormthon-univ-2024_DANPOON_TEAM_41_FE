package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class StoreFastListRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: Information
) {
    data class Information(
        @SerializedName("storeId") val storeId: Int,
        @SerializedName("storeName") val storeName: String,
        @SerializedName("storeIntro") val storeIntro: String,
        @SerializedName("storeAddress") val storeAddress: String,
        @SerializedName("storeImageUrl") val storeImageUrl: String,
        @SerializedName("star") val star: Double,
        @SerializedName("storeInfos") val storeInfos: ArrayList<StoreInfo>,
        @SerializedName("reviewCount") val reviewCount: Int
    ) {
        data class StoreInfo(
            @SerializedName("day") val day: String,
            @SerializedName("openTime") val openTime: String,
            @SerializedName("closeTime") val closeTime: String,
            @SerializedName("open") val `open`: Boolean
        )
    }
}