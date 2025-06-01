package com.example.hexagonal.car

import org.springframework.transaction.annotation.Transactional

open class CarService(
    private val savePort: CarSavePort,
    private val loadPort: CarLoadPort,
) : CarCommandUseCase, CarQueryUseCase {
    @Transactional
    override fun bulkCreateCar(commands: Collection<CarProperties>): Collection<CarModel> {
        return savePort.saveAll(commands)
    }

    override fun getByLicensePlateNumber(licensePlateNumber: LicensePlateNumber): CarModel {
        return loadPort.findByLicensePlateNumberOrNull(licensePlateNumber)
            ?: throw CarNotFoundException("등록되지 않은 자동차입니다")
    }
}
