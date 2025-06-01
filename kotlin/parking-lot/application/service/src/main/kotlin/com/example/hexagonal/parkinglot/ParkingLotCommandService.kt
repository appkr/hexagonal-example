package com.example.hexagonal.parkinglot

import com.example.hexagonal.car.CarCommandUseCase
import com.example.hexagonal.car.CarData
import com.example.hexagonal.car.CarNotFoundException
import com.example.hexagonal.car.CarQueryUseCase
import com.example.hexagonal.car.LicensePlateNumber
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

open class ParkingLotCommandService(
    private val parkingLot: ParkingLot,
    private val carQueryUseCase: CarQueryUseCase,
    private val carCommandUseCase: CarCommandUseCase,
) : ParkingLotCommandUseCase {
    @Transactional
    override fun checkIn(
        licensePlateNumber: LicensePlateNumber,
        enteredAt: Instant?,
    ): ParkingEvent {
        val car =
            try {
                carQueryUseCase.getByLicensePlateNumber(licensePlateNumber)
            } catch (e: CarNotFoundException) {
                carCommandUseCase.bulkCreateCar(listOf(CarData(licensePlateNumber))).first()
            }

        // ParkingEvent에 대한 Persist는 생략한다
        return parkingLot.enter(car, DefaultParkingFeePolicy(), enteredAt ?: Instant.now())
    }

    @Transactional
    override fun checkOut(
        licensePlateNumber: LicensePlateNumber,
        leavedAt: Instant?,
    ): ParkingEvent {
        val car = carQueryUseCase.getByLicensePlateNumber(licensePlateNumber)

        // 계산한 요금을 포함한 ParkingEvent에 대한 Persist는 생략한다
        // Persist 한다면 ParkingRecord로 이름 지을 것; 일 정산 등에서 사용하려면 Persist 해야 함
        return parkingLot.leave(car, leavedAt ?: Instant.now())
    }
}
