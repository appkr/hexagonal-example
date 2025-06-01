package com.example.hexagonal.parkinglot

import java.time.Duration

interface ParkingFeePolicy {
    val freeMinutes: Duration
    val unitMinutes: Duration
    val feePerUnit: Money

    // 다양한 전략 객체를 구현하기를 기대한다
    // Car 객체를 받아서 차등 요금을 부여하는 등의 기능은 생략했다
    fun calculate(parkingDuration: Duration): Money
}

class DefaultParkingFeePolicy : ParkingFeePolicy {
    override val freeMinutes = Duration.ofMinutes(30)
    override val unitMinutes = Duration.ofMinutes(10)
    override val feePerUnit = Money(100L.toBigDecimal())

    override fun calculate(parkingDuration: Duration): Money {
        if (parkingDuration <= freeMinutes) {
            return Money.ZERO
        }

        // 라운딩 정책등은 생략한다
        val chargedDuration: Duration = parkingDuration - freeMinutes
        val chargedUnit: Long = chargedDuration.dividedBy(unitMinutes)

        return feePerUnit * chargedUnit
    }
}
