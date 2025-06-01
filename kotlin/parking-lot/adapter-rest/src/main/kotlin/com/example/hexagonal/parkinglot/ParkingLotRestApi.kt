package com.example.hexagonal.parkinglot

import io.swagger.v3.oas.annotations.Operation
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.Valid
import java.time.Clock

@RestController
@RequestMapping(path = ["/parking-lots"])
@Validated
class ParkingLotRestApi(
    private val commandUseCase: ParkingLotCommandUseCase,
    private val queryUseCase: ParkingLotQueryUseCase,
    private val clock: Clock,
) {
    @Operation
    @PostMapping("/check-in")
    fun checkIn(
        @Valid @RequestBody request: CheckInRequest,
    ): ParkingTicket {
        return commandUseCase
            .checkIn(request.licensePlateNumber, clock.instant())
            .let { ParkingTicket(it) }
    }

    @Operation
    @PostMapping("/check-out")
    fun checkIn(
        @Valid @RequestBody request: CheckOutRequest,
    ): ParkingReceipt {
        return commandUseCase
            .checkOut(request.licensePlateNumber, clock.instant())
            .let { ParkingReceipt(it) }
    }
}
