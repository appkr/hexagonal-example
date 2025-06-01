package com.example.hexagonal

import org.springdoc.core.customizers.OpenApiCustomizer
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class SpringDocConfiguration {
    @Bean
    open fun carApiDoc(openApiCustomizers: List<OpenApiCustomizer>): GroupedOpenApi =
        GroupedOpenApi.builder()
            .group("Car")
            .pathsToMatch("/cars/**")
            .apply {
                openApiCustomizers.forEach {
                    addOpenApiCustomizer(it)
                }
            }
            .build()

    @Bean
    open fun parkingLotApiDoc(openApiCustomizers: List<OpenApiCustomizer>): GroupedOpenApi =
        GroupedOpenApi.builder()
            .group("ParkingLot")
            .pathsToMatch("/parking-lots/**")
            .apply {
                openApiCustomizers.forEach {
                    addOpenApiCustomizer(it)
                }
            }
            .build()
}
