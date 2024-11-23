package com.example.allgoing.retrofit.DTO.Request

import com.google.gson.annotations.SerializedName

data class CatAssChangeReq(
    @SerializedName("itemIds") val itemIds: ArrayList<Int>
)