package com.example.hexagonal.car

import io.swagger.v3.oas.annotations.media.Schema

data class CarDto(
    @Schema(readOnly = true)
    val carId: Long,
    val licensePlateNumber: String,
) {
    constructor(model: CarModel) : this(
        carId = model.identity.value,
        licensePlateNumber = model.licencePlateNumber.value,
    )

    fun toProps() = CarData(LicensePlateNumber(licensePlateNumber))
}
