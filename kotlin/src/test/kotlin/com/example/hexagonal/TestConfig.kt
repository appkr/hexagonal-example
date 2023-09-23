package com.example.hexagonal

import com.example.hexagonal.port.driven.MessagePort
import com.example.hexagonal.port.driven.ProductJpaRepository
import com.example.hexagonal.port.driving.ProductPort
import io.mockk.mockk
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Primary
import org.springframework.test.context.ActiveProfiles

@Configuration
@Import(HexagonalExampleApplication::class)
@ActiveProfiles("test")
class TestConfig {
    @Bean
    @Primary
    fun mockProductRepository(): ProductJpaRepository = mockk()

    @Bean
    @Primary
    fun mockMessagePort(): MessagePort = mockk()

    @Bean
    @Primary
    fun mockProductPort(): ProductPort = mockk()
}
