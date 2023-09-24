package com.example.hexagonal

import com.example.hexagonal.application.port.outbound.MessagePort
import com.example.hexagonal.application.port.outbound.ProductRepository
import com.example.hexagonal.application.port.inbound.ProductPort
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
    fun mockProductRepository(): ProductRepository = mockk()

    @Bean
    @Primary
    fun mockMessagePort(): MessagePort = mockk()

    @Bean
    @Primary
    fun mockProductPort(): ProductPort = mockk()
}
