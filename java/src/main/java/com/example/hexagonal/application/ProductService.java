package com.example.hexagonal.application;

import com.example.hexagonal.application.port.outbound.MessagePort;
import com.example.hexagonal.application.domain.Product;
import com.example.hexagonal.application.port.dto.ProductDto;
import com.example.hexagonal.application.port.inbound.ProductUsecase;
import com.example.hexagonal.application.port.outbound.ProductRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUsecase {

  final ProductRepository repository;
  final MessagePort messagePort;

  @Override
  @Transactional(readOnly = true)
  public Product getProduct(Long productId) {
    return findProductBy(productId);
  }

  @Override
  @Transactional
  public Product updateProduct(Long productId, ProductDto dto) {
    final Product product = findProductBy(productId);

    if (dto.getName() != null && !dto.getName().equals(product.getName())) {
      product.setName(dto.getName());
    }

    if (dto.getStock() != null && dto.getStock().equals(product.getStock())) {
      product.setStock(dto.getStock());
    }

    // Hypothetical scenario to show another outbound port
    messagePort.send("Product:id#" + product.getId() + " changed");

    return repository.save(product);
  }

  private Product findProductBy(Long productId) {
    return repository.findById(productId)
        .orElseThrow(() -> new NoSuchElementException());
  }
}
