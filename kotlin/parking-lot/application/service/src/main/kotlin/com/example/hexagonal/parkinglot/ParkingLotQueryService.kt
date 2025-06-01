package com.example.hexagonal.parkinglot

import com.example.hexagonal.car.CarNotFoundException
import com.example.hexagonal.car.LicensePlateNumber

class ParkingLotQueryService(
    private val parkingLot: ParkingLot,
) : ParkingLotQueryUseCase {
    override fun findParkingEvent(licensePlateNumber: LicensePlateNumber): ParkingEvent {
        return parkingLot.parkingEvents
            .firstOrNull { it.car.licencePlateNumber == licensePlateNumber }
            ?: throw CarNotFoundException("입차 내역이 없는 차량입니다")
    }

    override fun listAllParkingEvents(query: DateRange?): Collection<ParkingEvent> {
        return if (query == null) {
            parkingLot.parkingEvents
        } else {
            parkingLot.parkingEvents
                .filter { it.enteredAt.isAfter(query.from) }
                .filter { it.enteredAt.isBefore(query.to) }
        }
    }
}
