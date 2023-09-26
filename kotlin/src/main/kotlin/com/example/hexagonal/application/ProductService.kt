package com.example.hexagonal.application

import com.example.hexagonal.application.domain.Product
import com.example.hexagonal.application.port.outbound.MessagePort
import com.example.hexagonal.application.port.outbound.ProductRepository
import com.example.hexagonal.application.port.inbound.ProductUsecase
import com.example.hexagonal.application.port.dto.ProductDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ProductService(
    val repository: ProductRepository,
    val messagePort: MessagePort,
) : ProductUsecase {
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
