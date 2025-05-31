package com.example.hexagonal.car

class CarJpaAdapter : CarSavePort, CardLoadPort {
    override fun saveAll(cars: Collection<com.example.hexagonal.car.CarProperties>): Collection<com.example.hexagonal.car.CarModel> {
        TODO("Not yet implemented")
    }

    override fun findByLicensePlateNumberOrNull(
        licensePlateNumber: com.example.hexagonal.car.LicensePlateNumber,
    ): com.example.hexagonal.car.CarModel? {
        TODO("Not yet implemented")
    }
}
