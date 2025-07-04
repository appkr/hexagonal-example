package com.example.hexagonal.car

interface CarQueryUseCase {
    /**
     * @throws CarNotFoundException
     */
    fun getByLicensePlateNumber(licensePlateNumber: LicensePlateNumber): CarModel
}
