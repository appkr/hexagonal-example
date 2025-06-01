package com.example.hexagonal.car

import io.swagger.v3.oas.annotations.media.Schema
import java.time.OffsetDateTime
import java.time.ZoneOffset

data class CarDto(
    @field:Schema(readOnly = true)
    val carId: Long?,
    @field:Schema(description = "자동차 번호")
    val licensePlateNumber: String,
    @field:Schema(readOnly = true)
    val createdAt: OffsetDateTime?,
    @field:Schema(readOnly = true)
    val updatedAt: OffsetDateTime?,
) {
    constructor(model: CarModel) : this(
        carId = model.identity.value,
        licensePlateNumber = model.licencePlateNumber.value,
        createdAt = OffsetDateTime.ofInstant(model.createdAt, ZoneOffset.UTC),
        updatedAt = OffsetDateTime.ofInstant(model.updatedAt, ZoneOffset.UTC),
    )

    fun toProps() = CarData(LicensePlateNumber(licensePlateNumber))
}
