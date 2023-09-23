package com.example.hexagonal.port.out

import com.example.hexagonal.domain.Product
import com.example.hexagonal.port.driven.ProductJpaRepository
import io.kotest.assertions.fail
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.TestCase
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestConstructor
import kotlin.jvm.optionals.getOrNull

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ProductJpaRepositoryTest(
    val repository: ProductJpaRepository,
) : DescribeSpec() {
    override fun extensions() = listOf(SpringExtension)

    init {
        describe("When find an entity with id") {
            it("should should return an entity") {
                repository.findById(fixture.id)
                    .getOrNull()
                    ?.let {
                        it.id shouldBe fixture.id
                    }
                    ?: fail("Error")
            }
        }
    }

    lateinit var fixture: Product

    override suspend fun beforeTest(testCase: TestCase) {
        repository.deleteAll()
        fixture = repository.save(Product.aProduct())
    }
}
