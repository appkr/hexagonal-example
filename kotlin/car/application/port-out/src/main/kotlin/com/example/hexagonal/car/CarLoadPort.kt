package com.example.hexagonal.car

interface CarLoadPort {
    fun findByLicensePlateNumberOrNull(licensePlateNumber: LicensePlateNumber): CarModel?
}
