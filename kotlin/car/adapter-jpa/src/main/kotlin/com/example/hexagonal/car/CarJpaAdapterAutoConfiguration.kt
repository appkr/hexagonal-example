package com.example.hexagonal.car

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@AutoConfiguration
@EnableJpaRepositories
@EntityScan
@Import(CarJpaAdapter::class)
open class CarJpaAdapterAutoConfiguration
