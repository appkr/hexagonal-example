package com.example.hexagonal.port.driven

import com.example.hexagonal.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductJpaRepository : JpaRepository<Product, Long>
