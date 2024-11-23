package com.example.allgoing.retrofit.DTO.Response

data class StoreNoticeRes(
    val check: Boolean,
    val information: List<Information>
) {
    data class Information(
        val storeId: Int,
        val storeNoticeContent: String,
        val createdAt: String
    )
}