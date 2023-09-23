package com.example.hexagonal.adapter.driving

import com.example.hexagonal.port.driving.ProductPort
import com.example.hexagonal.usecase.dto.DtoMapper
import com.example.hexagonal.usecase.dto.ProductDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    val productPort: ProductPort,
    val dtoMapper: DtoMapper,
) {
    @GetMapping("/api/products/{productId}")
    fun getProduct(@PathVariable("productId") productId: Long): ResponseEntity<ProductDto> {
        return productPort.getProduct(productId)
            ?.let {
                ResponseEntity.ok(dtoMapper.toDto(it))
            }
            ?: ResponseEntity.notFound().build()
    }

    @PutMapping("/api/products/{productId}")
    fun updateProduct(
        @PathVariable("productId") productId: Long,
        @RequestBody dto: ProductDto,
    ): ResponseEntity<ProductDto> {
        return productPort.updateProduct(productId, dto)
            ?.let {
                ResponseEntity.ok(dtoMapper.toDto(it))
            }
            ?: ResponseEntity.notFound().build()
    }
}
