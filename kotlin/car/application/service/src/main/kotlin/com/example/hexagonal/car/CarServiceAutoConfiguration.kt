package com.example.hexagonal.car

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Import

@AutoConfiguration
@Import(CarService::class)
class CarServiceAutoConfiguration
