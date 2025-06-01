package com.example.hexagonal.parkinglot

import com.example.hexagonal.car.LicensePlateNumber

data class CheckOutRequest(
    val licensePlateNumber: LicensePlateNumber,
)
