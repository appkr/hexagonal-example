package com.example.hexagonal.adapter.inbound

import com.example.hexagonal.adapter.inbound.mapper.ProductMapper
import com.example.hexagonal.api.ProductApiDelegate
import com.example.hexagonal.api.model.ProductDto
import com.example.hexagonal.application.port.inbound.ProductPort
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class ProductApiDelegateImpl(
    val productPort: ProductPort,
    val productMapper: ProductMapper,
) : ProductApiDelegate {
    override fun getProduct(productId: Long): ResponseEntity<ProductDto> {
        return productPort.getProduct(productId)
            ?.let { ResponseEntity.ok(productMapper.toDto(it)) }
            ?: ResponseEntity.notFound().build()
    }

    override fun updateProduct(productId: Long, productDto: ProductDto?): ResponseEntity<ProductDto> {
        // TODO juwon
        return super.updateProduct(productId, productDto)
    }
}
