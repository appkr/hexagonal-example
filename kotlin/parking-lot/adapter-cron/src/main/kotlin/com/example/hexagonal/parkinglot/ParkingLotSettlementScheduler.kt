package com.example.hexagonal.parkinglot

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import java.time.Instant

class ParkingLotSettlementScheduler(
    private val parkingLotQueryUseCase: ParkingLotQueryUseCase,
) {
    private val logger = KotlinLogging.logger {}

    /**
     * 분 단위 정산 결과를 로그로 찍는다
     *
     *                 ┌───────────── second (0-59)
     *                 │ ┌───────────── minute (0 - 59)
     *                 │ │ ┌───────────── hour (0 - 23)
     *                 │ │ │ ┌───────────── day of the month (1 - 31)
     *                 │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
     *                 │ │ │ │ │ ┌───────────── day of the week (0 - 7)
     *                 │ │ │ │ │ │          (0 or 7 is Sunday, or MON-SUN)
     *                 │ │ │ │ │ │
     *                 * * * * * *
     */
    @Scheduled(cron = "0 * * * * *", zone = "Asia/Seoul")
    fun onEveryMinute() {
        logger.info { "정산을 시작합니다" }

        val now = Instant.now()
        parkingLotQueryUseCase
            .listAllParkingEvents(
                query = DateRange(now.minusSeconds(60), now),
            )
            .map { logger.info { it } }

        logger.info { "정산을 종료합니다" }
    }
}
