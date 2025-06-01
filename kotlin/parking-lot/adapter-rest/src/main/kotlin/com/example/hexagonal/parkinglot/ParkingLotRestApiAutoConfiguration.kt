package com.example.hexagonal.parkinglot

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Import

@AutoConfiguration
@Import(ParkingLotRestApi::class)
open class ParkingLotRestApiAutoConfiguration
