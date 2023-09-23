package com.example.hexagonal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HexagonalExampleApplication

fun main(args: Array<String>) {
    runApplication<HexagonalExampleApplication>(*args)
}

