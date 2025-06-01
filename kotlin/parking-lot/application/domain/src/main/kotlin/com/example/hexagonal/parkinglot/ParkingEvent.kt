package com.example.hexagonal.parkinglot

import com.example.hexagonal.car.CarModel
import java.time.Duration
import java.time.Instant

data class ParkingEvent(
    val car: CarModel,
    val feePolicy: ParkingFeePolicy,
    val enteredAt: Instant,
    val leavedAt: Instant? = null,
) {
    val parkingDuration: Duration =
        if (leavedAt == null) {
            Duration.between(enteredAt, Instant.now())
        } else {
            Duration.between(enteredAt, leavedAt)
        }

    val parkingFee: Money =
        feePolicy.calculate(parkingDuration)
}
