package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class StoreNoticeRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: ArrayList<Information>
) {
    data class Information(
        @SerializedName("storeId") val storeId: Int,
        @SerializedName("storeNoticeContent") val storeNoticeContent: String,
        @SerializedName("createdAt") val createdAt: String
    )
}