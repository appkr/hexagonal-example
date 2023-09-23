package com.example.hexagonal.usecase.dto;

import com.example.hexagonal.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

  public ProductDto toDto(Product entity) {
    return ProductDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .stock(entity.getStock())
        .build();
  }
}
