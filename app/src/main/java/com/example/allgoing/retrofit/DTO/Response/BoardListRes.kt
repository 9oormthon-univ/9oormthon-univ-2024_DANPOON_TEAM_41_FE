package com.example.allgoing.retrofit.DTO.Response

import com.example.allgoing.retrofit.DTO.DataClass.Board
import com.google.gson.annotations.SerializedName

data class BoardListRes(
    @SerializedName("check") val check: Boolean,
    @SerializedName("information") val information: ArrayList<Board>
)