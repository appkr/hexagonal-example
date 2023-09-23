package com.example.hexagonal.port.driving

import com.example.hexagonal.domain.Product
import com.example.hexagonal.usecase.dto.ProductDto

interface ProductPort {
    fun getProduct(productId: Long): Product
    fun updateProduct(productId: Long, dto: ProductDto): Product
}
