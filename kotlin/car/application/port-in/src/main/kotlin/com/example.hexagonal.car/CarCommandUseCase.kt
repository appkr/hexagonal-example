package com.example.hexagonal.car

interface CarCommandUseCase {
    fun bulkCreateCar(commands: Collection<CarProperties>): Collection<CarModel>
}
