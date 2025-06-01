package com.example.hexagonal.parkinglot

import com.example.hexagonal.car.LicensePlateNumber
import java.time.Instant

interface ParkingLotCommandUseCase {
    /**
     * 주차 티켓을 발권한다
     *
     * @throws CarNotFoundException
     */
    fun checkIn(
        licensePlateNumber: LicensePlateNumber,
        enteredAt: Instant? = Instant.now(),
    ): ParkingEvent

    /**
     * 요금을 정산한다
     *
     * @throws CarNotFoundException
     */
    fun checkOut(
        licensePlateNumber: LicensePlateNumber,
        leavedAt: Instant? = Instant.now(),
    ): ParkingEvent
}
