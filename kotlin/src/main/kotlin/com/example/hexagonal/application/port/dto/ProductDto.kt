package com.example.hexagonal.application.port.dto

import com.example.hexagonal.application.domain.Product.Companion.DEFAULT_NAME
import com.example.hexagonal.application.domain.Product.Companion.DEFAULT_STOCK

data class ProductDto(
    val id: Long,
    var name: String,
    var stock: Int? = 0,
) {
    companion object {
        fun aProductDtoOf(productId: Long) =
            ProductDto(id = productId, name = DEFAULT_NAME, stock = DEFAULT_STOCK)
    }
}
