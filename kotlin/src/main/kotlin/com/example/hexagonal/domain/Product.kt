package com.example.hexagonal.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.io.Serializable

@Entity
@Table(name = "products")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "pname")
    var name: String,

    var stock: Int? = 0,
) : Serializable {
    companion object {
        const val DEFAULT_ID = 1L
        const val DEFAULT_NAME = "Foo"
        const val DEFAULT_STOCK = 1

        fun aProduct() = Product(id = DEFAULT_ID, name = DEFAULT_NAME, stock = DEFAULT_STOCK)
    }
}
