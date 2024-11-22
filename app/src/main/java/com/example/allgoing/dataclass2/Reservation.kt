package com.example.allgoing.dataclass2

data class Reservation(
    val reservationId : Int,
    val memberId : Int,
    val storeId : Int,
    val reservationDate : String
)
