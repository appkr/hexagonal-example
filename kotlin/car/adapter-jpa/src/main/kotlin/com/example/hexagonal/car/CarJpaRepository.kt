package com.example.hexagonal.car

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CarJpaRepository : JpaRepository<CarJpaEntity, UUID> {
    fun findByLicensePlateNumber(value: String): CarJpaEntity?
}
