package com.example.hexagonal.adapter.in;

import com.example.hexagonal.port.dto.DtoFixture;
import com.example.hexagonal.port.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

  @GetMapping("/api/products/{productId}")
  public ResponseEntity<ProductDto> getProduct(@PathVariable("productId") Long productId) {
    return ResponseEntity.ok(DtoFixture.aProductDtoOf(productId));
  }

  @PutMapping("/api/products/{productId}")
  public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") Long productId,
      @RequestBody ProductDto dto) {
    return ResponseEntity.ok(dto);
  }
}
