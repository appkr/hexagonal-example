package com.example.hexagonal.application.port.outbound;

import static com.example.hexagonal.application.domain.ModelFixture.DEFAULT_ID;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.hexagonal.application.port.outbound.ProductRepository;
import com.example.hexagonal.application.domain.ModelFixture;
import com.example.hexagonal.application.domain.Product;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductRepositoryTest {

  @Autowired
  ProductRepository repository;

  @BeforeEach
  void setup() {
    repository.deleteAll();
    repository.save(ModelFixture.aProduct());
  }

  @Test
  void findById() {
    final Product product = repository.findById(DEFAULT_ID)
        .orElseThrow(() -> new NoSuchElementException());

    assertThat(product.getId()).isEqualTo(DEFAULT_ID);
  }
}
