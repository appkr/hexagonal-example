package com.example.hexagonal.car

import org.springframework.transaction.annotation.Transactional

open class CarService : CarCommandUseCase, CarQueryUseCase {
    @Transactional
    override fun bulkCreateCar(commands: Collection<CarProperties>): Collection<CarModel> {
        TODO("Not yet implemented")
    }

    override fun getByLicensePlateNumber(licensePlateNumber: LicensePlateNumber): CarModel {
        TODO("Not yet implemented")
    }
}
