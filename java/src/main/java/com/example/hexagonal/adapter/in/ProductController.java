package com.example.hexagonal.adapter.in;

import com.example.hexagonal.domain.Product;
import com.example.hexagonal.usecase.dto.DtoMapper;
import com.example.hexagonal.usecase.dto.ProductDto;
import com.example.hexagonal.port.in.ProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

  final ProductPort productPort;
  final DtoMapper dtoMapper;

  @GetMapping("/api/products/{productId}")
  public ResponseEntity<ProductDto> getProduct(@PathVariable("productId") Long productId) {
    final Product entity = productPort.getProduct(productId);
    return ResponseEntity.ok(dtoMapper.toDto(entity));
  }

  @PutMapping("/api/products/{productId}")
  public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") Long productId,
      @RequestBody ProductDto dto) {
    final Product entity = productPort.updateProduct(productId, dto);
    return ResponseEntity.ok(dtoMapper.toDto(entity));
  }
}
