package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class ReservationMyRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: ArrayList<Information>
) {
    data class Information(
        @SerializedName("reservationId") val reservationId: Int,
        @SerializedName("reservationStatus") val reservationStatus: String,
        @SerializedName("reservationDate") val reservationDate: String,
        @SerializedName("reservationTime") val reservationTime: String,
        @SerializedName("storeId") val storeId: Int,
        @SerializedName("storeName") val storeName: String,
        @SerializedName("products") val products: ArrayList<Product>
    ) {
        data class Product(
            @SerializedName("productId") val productId: Int,
            @SerializedName("productName") val productName: String,
            @SerializedName("quantity") val quantity: Int
        )
    }
}