package com.example.hexagonal.car

class CarJpaAdapter(
    private val repository: CarJpaRepository,
) : CarSavePort, CardLoadPort {
    override fun saveAll(cars: Collection<CarProperties>): Collection<CarModel> {
        return cars
            .map { CarJpaEntity(it) }
            .let { repository.saveAll(it) }
            .map { it.toModel() }
    }

    override fun findByLicensePlateNumberOrNull(licensePlateNumber: LicensePlateNumber): CarModel? {
        return repository
            .findByLicensePlateNumber(licensePlateNumber.value)
            ?.toModel()
    }
}
