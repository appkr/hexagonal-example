package team.example.hexagonal.car

import com.example.hexagonal.car.CarData
import com.example.hexagonal.car.LicensePlateNumber
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData

class CarTest : DescribeSpec() {
    init {
        describe("new") {
            withData(
                nameFn = { "$it -> 성공" },
                ts =
                    listOf(
                        "서울 01가 1111",
                        "서울01가1111",
                        "01가 1111",
                        "01가1111",
                    ),
            ) {
                shouldNotThrowAny {
                    CarData(LicensePlateNumber(it))
                }
            }

            withData(
                nameFn = { "$it -> 실패" },
                ts =
                    listOf(
                        "AU DEVOPS",
                        "AB 1234 CD",
                        "서울 999이 123",
                        "1234가 5678",
                    ),
            ) {
                shouldThrow<IllegalArgumentException> {
                    CarData(LicensePlateNumber(it))
                }
            }
        }
    }
}
