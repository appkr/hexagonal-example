package com.example.hexagonal.parkinglot

import com.example.hexagonal.car.CarEntity
import com.example.hexagonal.car.CarKey
import com.example.hexagonal.car.LicensePlateNumber
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.UUID

class ParkingLotTest : DescribeSpec() {
    init {
        describe("enter / leave") {
            withData(
                nameFn = { "$it" },
                ts =
                    listOf(
                        row(20L, Money.ZERO),
                        row(60L, Money(300L.toBigDecimal())),
                        row(101L, Money(700L.toBigDecimal())),
                    ),
            ) { (parkingMinutes, parkingFee) ->
                val sut = ParkingLot()
                val aCar =
                    CarEntity(
                        identity = CarKey(UUID.randomUUID()),
                        licencePlateNumber = LicensePlateNumber("서울 111 가 1111"),
                        createdAt = Instant.EPOCH,
                        updatedAt = Instant.EPOCH,
                    )

                sut.enter(aCar, DefaultParkingFeePolicy(), Instant.EPOCH)
                sut.parkingEvents shouldHaveSize 1

                val parkingEvent = sut.leave(aCar, Instant.EPOCH.plus(parkingMinutes, ChronoUnit.MINUTES))
                sut.parkingEvents shouldHaveSize 0

                parkingEvent.parkingFee shouldBe parkingFee
            }
        }
    }
}
