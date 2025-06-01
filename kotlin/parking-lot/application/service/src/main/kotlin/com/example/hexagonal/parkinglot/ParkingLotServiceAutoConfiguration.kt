package com.example.hexagonal.parkinglot

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@AutoConfiguration
@Import(
    ParkingLotCommandService::class,
)
open class ParkingLotServiceAutoConfiguration {
    @Bean
    open fun singletonParkingLot(): ParkingLot = ParkingLot()
}
