package com.example.hexagonal.parkinglot

import java.time.Duration
import java.time.OffsetDateTime
import java.time.ZoneOffset

data class ParkingReceipt(
    val carId: String,
    val licensePlateNumber: String,
    val enteredAt: OffsetDateTime,
    val freeMinutes: Duration,
    val unitMinutes: Duration,
    val feePerUnit: Money,
    val parkingDuration: Duration,
    val parkingFee: Money,
) {
    constructor(parkingEvent: ParkingEvent) : this(
        carId = parkingEvent.car.identity.value.toString(),
        licensePlateNumber = parkingEvent.car.licencePlateNumber.value,
        enteredAt = OffsetDateTime.ofInstant(parkingEvent.enteredAt, ZoneOffset.UTC),
        freeMinutes = parkingEvent.feePolicy.freeMinutes,
        unitMinutes = parkingEvent.feePolicy.unitMinutes,
        feePerUnit = parkingEvent.feePolicy.feePerUnit,
        parkingDuration = parkingEvent.parkingDuration,
        parkingFee = parkingEvent.parkingFee,
    )
}
