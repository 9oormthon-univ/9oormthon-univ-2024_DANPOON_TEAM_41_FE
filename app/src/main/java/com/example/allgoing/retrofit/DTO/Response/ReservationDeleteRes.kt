package com.example.allgoing.retrofit.DTO.Response

import com.google.gson.annotations.SerializedName

data class ReservationDeleteRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: String
)