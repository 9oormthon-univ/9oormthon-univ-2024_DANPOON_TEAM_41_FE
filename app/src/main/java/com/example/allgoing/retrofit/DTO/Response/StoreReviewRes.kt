package com.example.allgoing.retrofit.DTO.Response

import com.example.allgoing.retrofit.DTO.DataClass.Review
import com.google.gson.annotations.SerializedName

data class StoreReviewRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: ArrayList<Review>
)