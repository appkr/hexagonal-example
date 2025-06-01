package com.example.hexagonal.car

import io.swagger.v3.oas.annotations.Operation
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.Valid

@RestController
@RequestMapping(path = ["/cars"])
@Validated
class CarRestApi(
    private val commandUseCase: CarCommandUseCase,
    private val queryUseCase: CarQueryUseCase,
) {
    @Operation(tags = ["Cars"], summary = "차량 등록")
    @PostMapping
    fun bulkCreateCars(
        @Valid @RequestBody request: Collection<CarDto>,
    ): Collection<CarDto> {
        return commandUseCase
            .bulkCreateCar(
                commands = request.map { it.toProps() },
            )
            .map { CarDto(it) }
    }

    @Operation(tags = ["Cars"], summary = "차량 조회")
    @GetMapping
    fun getCar(
        @RequestParam licensePlateNumber: String,
    ): CarDto {
        return queryUseCase
            .getByLicensePlateNumber(LicensePlateNumber(licensePlateNumber))
            .let { CarDto(it) }
    }
}
