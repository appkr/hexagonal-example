package com.example.hexagonal.parkinglot

import com.example.hexagonal.car.LicensePlateNumber

data class CheckInRequest(
    val licensePlateNumber: LicensePlateNumber,
)
