package com.example.allgoing.retrofit.DTO.Response

data class CatAssListRes(
    val check: Boolean,
    val information: Information
) {
    data class Information(
        val coin: Int,
        val catItems: List<CatItem>
    ) {
        data class CatItem(
            val itemId: Int,
            val itemCategory: String,
            val itemName: String,
            val itemPrice: Int
        )
    }
}