package com.example.allgoing.retrofit.DTO.Response

import com.example.allgoing.retrofit.DTO.DataClass.Review
import com.example.allgoing.retrofit.DTO.DataClass.ReviewImg
import com.google.gson.annotations.SerializedName

data class ReviewDetailRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: Review
)