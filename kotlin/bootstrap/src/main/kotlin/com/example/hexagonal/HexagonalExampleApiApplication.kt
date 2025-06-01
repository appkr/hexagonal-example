package com.example.hexagonal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.Clock

@SpringBootApplication
open class HexagonalApplication {
    @Bean
    open fun clock(): Clock = Clock.systemUTC()
}

fun main(args: Array<String>) {
    runApplication<HexagonalApplication>(*args)
}
