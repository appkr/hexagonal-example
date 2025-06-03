package com.example.hexagonal

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.TestConstructor

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class HexagonalExampleApiApplicationTests(private val restTemplate: TestRestTemplate) {
    @Test
    fun test() {
        restTemplate.getForEntity<Any>("/actuator/health/liveness").also {
            assertThat(it.statusCode).isEqualTo(HttpStatus.OK)
        }

        restTemplate.getForEntity<Any>("/actuator/health/readiness").also {
            assertThat(it.statusCode).isEqualTo(HttpStatus.OK)
        }
    }
}
