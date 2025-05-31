package com.example.hexagonal.car

interface CarProperties {
    val licencePlateNumber: LicensePlateNumber
    // 차종, 소유자 등등의 속성은 생략한다
}

interface CarIdentity {
    val value: Long
}

interface CarModel : CarProperties {
    val identity: CarIdentity
}

data class CarData(
    override val licencePlateNumber: LicensePlateNumber,
) : CarProperties

data class CarKey(
    override val value: Long,
) : CarIdentity

data class CarEntity(
    override val licencePlateNumber: LicensePlateNumber,
    override val identity: CarIdentity,
) : CarModel
