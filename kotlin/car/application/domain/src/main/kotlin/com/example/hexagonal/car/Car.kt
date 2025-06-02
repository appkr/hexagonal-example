package com.example.hexagonal.car

import java.time.Instant
import java.util.UUID

interface CarProperties {
    val licencePlateNumber: LicensePlateNumber
    // 차종, 소유자 등등의 속성은 생략한다
}

interface CarIdentity {
    val value: UUID
}

interface CarModel : CarProperties {
    val identity: CarIdentity
    val createdAt: Instant
    val updatedAt: Instant
}

data class CarData(
    override val licencePlateNumber: LicensePlateNumber,
) : CarProperties

data class CarKey(
    override val value: UUID,
) : CarIdentity

data class CarEntity(
    override val licencePlateNumber: LicensePlateNumber,
    override val identity: CarIdentity,
    override val createdAt: Instant,
    override val updatedAt: Instant,
) : CarModel
