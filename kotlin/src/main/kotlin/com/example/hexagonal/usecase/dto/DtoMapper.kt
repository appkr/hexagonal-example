package com.example.hexagonal.usecase.dto

import com.example.hexagonal.domain.Product
import org.springframework.stereotype.Component

@Component
class DtoMapper {
    fun toDto(entity: Product): ProductDto =
        ProductDto(
            id = entity.id,
            name = entity.name,
            stock = entity.stock,
        )
}
