package com.example.hexagonal.usecase;

import com.example.hexagonal.domain.Product;
import com.example.hexagonal.port.out.MessagePort;
import com.example.hexagonal.usecase.dto.ProductDto;
import com.example.hexagonal.port.in.ProductPort;
import com.example.hexagonal.port.out.ProductJpaRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductPort {

  final ProductJpaRepository repository;
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
