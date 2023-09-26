package com.example.hexagonal.adapter.inbound.mapper

import com.example.hexagonal.api.model.ProductDto
import com.example.hexagonal.application.domain.Product
import org.springframework.stereotype.Component

@Component
class ProductMapper(
    val dummyCodeMapper: DummyCodeMapper,
) : DtoMapper<Product, ProductDto>() {
    override fun toDto(entity: Product): ProductDto {
        return ProductDto(
            productId = entity.id,
            name = entity.name,
            stock = entity.stock,
            dummyArray = dummyCodeMapper.toDto(listOf("TYPE_A")),
        )
    }
}
