package com.example.hexagonal.application.port.dto;

import static com.example.hexagonal.application.domain.ModelFixture.DEFAULT_NAME;
import static com.example.hexagonal.application.domain.ModelFixture.DEFAULT_STOCK;

public class DtoFixture {

  public static ProductDto aProductDtoOf(Long productId) {
    return ProductDto.builder()
        .id(productId)
        .name(DEFAULT_NAME)
        .stock(DEFAULT_STOCK)
        .build();
  }
}
