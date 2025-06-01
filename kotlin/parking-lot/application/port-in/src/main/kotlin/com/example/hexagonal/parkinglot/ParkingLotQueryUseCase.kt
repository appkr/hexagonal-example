package com.example.hexagonal.parkinglot

import com.example.hexagonal.car.LicensePlateNumber
import java.time.Instant

interface ParkingLotQueryUseCase {
    /**
     * @throws CarNotFoundException
     */
    fun findParkingEvent(licensePlateNumber: LicensePlateNumber): ParkingEvent

    fun listAllParkingEvents(query: DateRange?): Collection<ParkingEvent>
}

data class DateRange(val from: Instant, val to: Instant)
