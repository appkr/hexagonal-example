package com.example.hexagonal.adapter.inbound.mapper

import com.example.hexagonal.api.model.DummyCodeDto
import org.springframework.stereotype.Component

@Component
class DummyCodeMapper : DtoMapper<String, DummyCodeDto>() {
    override fun toDto(entity: String): DummyCodeDto {
        return enumValueOf<DummyCodeDto>(entity)
    }
}
