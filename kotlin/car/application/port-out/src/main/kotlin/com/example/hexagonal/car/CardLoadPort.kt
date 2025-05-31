package com.example.hexagonal.car

interface CardLoadPort {
    fun findByLicensePlateNumberOrNull(licensePlateNumber: LicensePlateNumber): CarModel?
}
