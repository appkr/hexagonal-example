package com.example.hexagonal.car

interface CarSavePort {
    fun saveAll(cars: Collection<CarProperties>): Collection<CarModel>
}
