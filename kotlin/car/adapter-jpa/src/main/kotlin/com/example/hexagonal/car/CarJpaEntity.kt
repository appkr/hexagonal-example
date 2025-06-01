package com.example.hexagonal.car

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "cars")
@EntityListeners(AuditingEntityListener::class)
class CarJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val licensePlateNumber: String = "",
    @CreatedDate
    val createdAt: Instant = Instant.now(),
    @LastModifiedDate
    val updatedAt: Instant = Instant.now(),
) {
    constructor(properties: CarProperties) : this(
        licensePlateNumber = properties.licencePlateNumber.value,
    )

    fun toModel() =
        CarEntity(
            identity = CarKey(id),
            licencePlateNumber = LicensePlateNumber(licensePlateNumber),
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
}
