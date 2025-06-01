package com.example.hexagonal.parkinglot

import java.math.BigDecimal

@JvmInline
value class Money(
    // Common 모듈이 적절한 위치이지만... 편의상 여기에 둔다
    val value: BigDecimal,
) {
    init {
        require(value >= BigDecimal.ZERO) { "양수 값만 허용합니다" }
    }

    operator fun times(multiplier: Long) = Money(value.times(BigDecimal(multiplier)))

    companion object {
        val ZERO = Money(BigDecimal.ZERO)
    }
}
