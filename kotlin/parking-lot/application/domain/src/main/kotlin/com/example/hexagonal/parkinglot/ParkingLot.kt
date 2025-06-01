package com.example.hexagonal.parkinglot

import com.example.hexagonal.car.CarModel
import com.example.hexagonal.car.CarNotFoundException
import java.time.Instant

class ParkingLot(
    // 주차 공간수, 구역, 위치 등등의 속성은 생략한다
    // 간략함을 위해 Persist 기능을 구현하지 않는다
    val parkingEvents: MutableSet<ParkingEvent> = mutableSetOf(),
) {
    fun enter(
        car: CarModel,
        feePolicy: ParkingFeePolicy,
        enteredAt: Instant,
    ) {
        val parkingEvent =
            ParkingEvent(
                car = car,
                feePolicy = feePolicy,
                enteredAt = enteredAt,
            )

        parkingEvents.add(parkingEvent)
    }

    fun leave(
        car: CarModel,
        leavedAt: Instant,
    ): ParkingEvent {
        val parkingEvent =
            parkingEvents.firstOrNull { it.car == car }
                ?: throw CarNotFoundException("입차 기록이 없는 자동차입니다")

        parkingEvents.remove(parkingEvent)

        return parkingEvent.copy(leavedAt = leavedAt)
    }
}
