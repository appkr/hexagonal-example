package com.example.hexagonal.usecase

import com.example.hexagonal.domain.Product
import com.example.hexagonal.port.driven.MessagePort
import com.example.hexagonal.port.driven.ProductJpaRepository
import com.example.hexagonal.port.driving.ProductPort
import com.example.hexagonal.usecase.dto.ProductDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    val repository: ProductJpaRepository,
    val messagePort: MessagePort,
) : ProductPort {
    @Transactional(readOnly = true)
    override fun getProduct(productId: Long): Product {
        return findProductBy(productId)
    }

    @Transactional
    override fun updateProduct(productId: Long, dto: ProductDto): Product {
        val product = findProductBy(productId)

        if (dto.name != null && dto.name != product.name) {
            product.name = dto.name
        }

        if (dto.stock != null && dto.stock != product.stock) {
            product.stock = dto.stock
        }

        messagePort.send("Product:id#$productId changed")

        return repository.save(product)
    }

    private fun findProductBy(productId: Long): Product {
        return repository.findById(productId)
            .orElseThrow { NoSuchElementException() }
    }
}
