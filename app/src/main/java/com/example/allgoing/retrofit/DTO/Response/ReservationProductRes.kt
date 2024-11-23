package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class ReservationProductRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: ArrayList<Information>
) {
    data class Information(
        @SerializedName("productId") val productId: Int,
        @SerializedName("productName") val productName: String,
        @SerializedName("productPrice") val productPrice: Int,
        @SerializedName("productImageUrl") val productImageUrl: String
    )
}