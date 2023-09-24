package com.example.hexagonal.application.port.outbound

import com.example.hexagonal.application.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>
