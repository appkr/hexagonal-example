package com.example.hexagonal.application.port.inbound

import com.example.hexagonal.application.domain.Product
import com.example.hexagonal.application.port.dto.ProductDto

interface ProductPort {
    fun getProduct(productId: Long): Product
    fun updateProduct(productId: Long, dto: ProductDto): Product
}
