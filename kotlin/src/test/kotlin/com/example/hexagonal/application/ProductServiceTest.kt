package com.example.hexagonal.application

import com.example.hexagonal.TestConfig
import com.example.hexagonal.application.ProductService
import com.example.hexagonal.application.domain.Product
import com.example.hexagonal.application.port.dto.ProductDto
import com.example.hexagonal.application.port.outbound.MessagePort
import com.example.hexagonal.application.port.outbound.ProductRepository
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestConstructor
import java.util.Optional

@SpringBootTest
@Import(TestConfig::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ProductServiceTest(
    sut: ProductService,
    mockRepository: ProductRepository,
    mockMessagePort: MessagePort,
) : DescribeSpec() {
    override fun extensions() = listOf(SpringExtension)

    init {
        describe("When calls 'getProduct' api") {
            context("query a product with id") {
                it("should returns an entity") {
                    every { mockRepository.findById(any<Long>()) } returns Optional.of(fixture)

                    val actual = sut.getProduct(fixture.id)

                    actual.id shouldBe fixture.id
                }
            }

            context("update a product ") {
                it("should now throw exception") {
                    every { mockRepository.findById(any<Long>()) } returns Optional.of(fixture)
                    every { mockRepository.save(any()) } returns fixture
                    every { mockMessagePort.send(any()) } returns Unit

                    val dto = ProductDto.aProductDtoOf(fixture.id)
                        .apply {
                            this.name = "changed"
                            this.stock = 2
                        }

                    shouldNotThrow<Exception> {
                        sut.updateProduct(fixture.id, dto)
                    }
                }
            }
        }
    }

    val fixture: Product = Product.aProduct()
}
