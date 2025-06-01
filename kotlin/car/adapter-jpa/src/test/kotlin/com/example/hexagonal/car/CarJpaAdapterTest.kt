package com.example.hexagonal.car

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@Import(CarJpaAdapter::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
open class CarJpaAdapterTest(
    private val sut: CarJpaAdapter,
) : DescribeSpec() {
    init {
        describe("saveAll") {
            context("Car 객체를 저장하면") {
                val cars =
                    listOf(
                        CarData(LicensePlateNumber("서울 111가 1111")),
                        CarData(LicensePlateNumber("111나 1111")),
                    )

                it("저장된다") {
                    sut.saveAll(cars)

                    sut.findByLicensePlateNumberOrNull(LicensePlateNumber("서울 111가 1111")) shouldNotBe null
                    sut.findByLicensePlateNumberOrNull(LicensePlateNumber("111나 1111")) shouldNotBe null
                }
            }
        }
    }

    override fun extensions() = listOf(SpringExtension)
}
