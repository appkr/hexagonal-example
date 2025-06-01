package com.example.hexagonal.car

import org.springframework.data.jpa.repository.JpaRepository

interface CarJpaRepository : JpaRepository<CarJpaEntity, Long> {
    fun findByLicensePlateNumber(value: String): CarJpaEntity?
}
