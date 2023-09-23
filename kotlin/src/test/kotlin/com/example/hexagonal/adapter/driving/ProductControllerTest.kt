package com.example.hexagonal.adapter.driving

import com.example.hexagonal.TestConfig
import com.example.hexagonal.domain.Product
import com.example.hexagonal.port.driving.ProductPort
import com.example.hexagonal.usecase.dto.DtoMapper
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.every
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@WebMvcTest(ProductController::class)
@Import(TestConfig::class, DtoMapper::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal open class ProductControllerTest(
    val mockUsecase: ProductPort,
    val dtoMapper: DtoMapper,
) : DescribeSpec() {
    override fun extensions() = listOf(SpringExtension)

    init {
        describe("When an API client calls 'getProduct' API") {
            it("should success with 2xx status") {
                getProduct()
            }
        }

        describe("When an API client calls 'updateProduct' API") {
            it("should success with 2xx status") {
                updateProduct()
            }
        }
    }

    open fun getProduct() {
        every { mockUsecase.getProduct(any()) } returns Product.aProduct()

        val res: ResultActions = mvc
            .perform {
                MockMvcRequestBuilders
                    .get("/api/products/{productId}", fixture.id)
                    .accept("application/json")
                    .buildRequest(it)
            }
            .andDo(MockMvcResultHandlers.print())

        res
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
    }

    open fun updateProduct() {
        val dto = Product.aProduct()
        every { mockUsecase.updateProduct(any(), any()) } returns dto

        val res: ResultActions = mvc
            .perform {
                MockMvcRequestBuilders
                    .put("/api/products/{productId}", fixture.id)
                    .contentType("application/json")
                    .accept("application/json")
                    .content(mapper.writeValueAsBytes(dto))
                    .buildRequest(it)
            }
            .andDo(MockMvcResultHandlers.print())

        res
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
    }

    val mvc: MockMvc = MockMvcBuilders
        .standaloneSetup(ProductController(mockUsecase, dtoMapper))
        .build()

    val fixture: Product = Product.aProduct()

    companion object {
        val mapper = ObjectMapper()
    }
}
