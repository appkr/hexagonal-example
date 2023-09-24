package com.example.hexagonal.adapter.inbound;

import com.example.hexagonal.application.domain.Product;
import com.example.hexagonal.application.port.dto.DtoMapper;
import com.example.hexagonal.application.port.dto.ProductDto;
import com.example.hexagonal.application.port.inbound.ProductPort;
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
