package com.example.hexagonal.port.in;

import com.example.hexagonal.domain.Product;
import com.example.hexagonal.usecase.dto.ProductDto;

public interface ProductPort {

  Product getProduct(Long productId);
  Product updateProduct(Long productId, ProductDto dto);
}
