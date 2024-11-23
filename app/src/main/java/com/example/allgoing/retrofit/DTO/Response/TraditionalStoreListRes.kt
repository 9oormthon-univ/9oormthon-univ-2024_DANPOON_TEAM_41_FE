package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class TraditionalStoreListRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: ArrayList<Information>
) {
    data class Information(
        @SerializedName("storeId") val storeId: Int,
        @SerializedName("storeName") val storeName: String,
        @SerializedName("storeIntro") val storeIntro: String,
        @SerializedName("storeAddress") val storeAddress: String,
        @SerializedName("star") val star: Double,
        @SerializedName("storeLatitude") val storeLatitude: Double,
        @SerializedName("storeLongitude") val storeLongitude: Double
    )
}