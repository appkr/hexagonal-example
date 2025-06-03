package com.example.hexagonal.parkinglot

import com.example.hexagonal.car.CarCommandUseCase
import com.example.hexagonal.car.CarData
import com.example.hexagonal.car.CarEntity
import com.example.hexagonal.car.CarKey
import com.example.hexagonal.car.CarNotFoundException
import com.example.hexagonal.car.CarQueryUseCase
import com.example.hexagonal.car.LicensePlateNumber
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.time.Instant
import java.util.UUID

class ParkingLotCommandServiceTest : DescribeSpec() {
    init {
        val licensePlateNumber = LicensePlateNumber("서울 111 가 1111")
        val aCar =
            CarEntity(
                identity = CarKey(UUID.randomUUID()),
                licencePlateNumber = licensePlateNumber,
                createdAt = Instant.EPOCH,
                updatedAt = Instant.EPOCH,
            )

        describe("checkIn") {
            context("입차한 이력이 있는 차량이라면") {
                val parkingLot = ParkingLot()
                val mockCarQueryUseCase =
                    mockk<CarQueryUseCase> {
                        every { getByLicensePlateNumber(licensePlateNumber) } returns aCar
                    }
                val mockCarCommandUseCase = mockk<CarCommandUseCase>()

                val sut =
                    ParkingLotCommandService(
                        parkingLot = parkingLot,
                        carQueryUseCase = mockCarQueryUseCase,
                        carCommandUseCase = mockCarCommandUseCase,
                    )

                it("입차 처리한다") {
                    val parkingEvent = sut.checkIn(licensePlateNumber)

                    parkingEvent.car shouldBe aCar
                    parkingLot.parkingEvents.size shouldBe 1
                    parkingLot.parkingEvents.firstOrNull { it.car.licencePlateNumber == licensePlateNumber } shouldNotBe null

                    verify(exactly = 0) { mockCarCommandUseCase.bulkCreateCar(any()) }
                }
            }

            context("입차한 이력이 없는 차량이라면") {
                val parkingLot = ParkingLot()
                val mockCarQueryUseCase =
                    mockk<CarQueryUseCase> {
                        every { getByLicensePlateNumber(licensePlateNumber) } throws CarNotFoundException("")
                    }
                val mockCarCommandUseCase =
                    mockk<CarCommandUseCase> {
                        every { bulkCreateCar(listOf(CarData(licensePlateNumber))) } returns listOf(aCar)
                    }

                val sut =
                    ParkingLotCommandService(
                        parkingLot = parkingLot,
                        carQueryUseCase = mockCarQueryUseCase,
                        carCommandUseCase = mockCarCommandUseCase,
                    )

                it("차량을 등록하고 입차 처리한다") {
                    val parkingEvent = sut.checkIn(licensePlateNumber)

                    parkingEvent.car shouldBe aCar
                    parkingLot.parkingEvents.size shouldBe 1
                    parkingLot.parkingEvents.firstOrNull { it.car.licencePlateNumber == licensePlateNumber } shouldNotBe null

                    verify(exactly = 1) { mockCarCommandUseCase.bulkCreateCar(any()) }
                }
            }
        }

        describe("checkOut") {
            context("입차된 차량이라면") {
                val parkingLot = ParkingLot()

                val sut =
                    ParkingLotCommandService(
                        parkingLot = parkingLot,
                        carQueryUseCase =
                            mockk<CarQueryUseCase> {
                                every { getByLicensePlateNumber(licensePlateNumber) } returns aCar
                            },
                        carCommandUseCase = mockk<CarCommandUseCase>(),
                    )

                sut.checkIn(licensePlateNumber)

                it("출차 처리한다") {
                    val parkingEvent =
                        shouldNotThrowAny {
                            sut.checkOut(licensePlateNumber)
                        }

                    parkingEvent.leavedAt shouldNotBe null
                    parkingLot.parkingEvents.size shouldBe 0
                }
            }

            describe("checkOut") {
                context("입차한 적이 없는 차량이라면") {
                    val parkingLot = ParkingLot()

                    val sut =
                        ParkingLotCommandService(
                            parkingLot = parkingLot,
                            carQueryUseCase =
                                mockk<CarQueryUseCase> {
                                    every { getByLicensePlateNumber(licensePlateNumber) } throws CarNotFoundException("")
                                },
                            carCommandUseCase = mockk<CarCommandUseCase>(),
                        )

                    it("출차 처리한다") {
                        shouldThrow<CarNotFoundException> {
                            sut.checkOut(licensePlateNumber)
                        }
                    }
                }
            }
        }
    }
}
