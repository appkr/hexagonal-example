package com.example.hexagonal.application.port.inbound;

import com.example.hexagonal.application.port.dto.ProductDto;
import com.example.hexagonal.application.domain.Product;

public interface ProductUsecase {

  Product getProduct(Long productId);
  Product updateProduct(Long productId, ProductDto dto);
}
