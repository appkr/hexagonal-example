package com.example.hexagonal

import com.example.hexagonal.parkinglot.ParkingLotSettlementScheduler
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.scheduling.annotation.EnableScheduling
import java.time.Clock

@SpringBootApplication(
    exclude = [
        ,
        // cron 앱 수행에 불필요하여 제외할 AutoConfiguration을 선언한다
    ],
)
@EnableScheduling
@Import(ParkingLotSettlementScheduler::class)
open class HexagonalExampleCronApplication {
    @Bean
    open fun clock(): Clock = Clock.systemUTC()
}

fun main(args: Array<String>) {
    runApplication<HexagonalExampleCronApplication>(*args)
}
