package com.example.hexagonal.port.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ProductDto {

  Long id;
  String name;
  Integer stock;

  public ProductDto() {
  }

  @Builder
  public ProductDto(Long id, String name, Integer stock) {
    this.id = id;
    this.name = name;
    this.stock = stock;
  }
}
