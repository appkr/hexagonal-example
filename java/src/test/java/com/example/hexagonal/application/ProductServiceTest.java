package com.example.hexagonal.application;

import static com.example.hexagonal.application.domain.ModelFixture.DEFAULT_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import com.example.hexagonal.application.ProductService;
import com.example.hexagonal.application.domain.ModelFixture;
import com.example.hexagonal.application.domain.Product;
import com.example.hexagonal.application.port.outbound.ProductRepository;
import com.example.hexagonal.application.port.dto.DtoFixture;
import com.example.hexagonal.application.port.dto.ProductDto;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ProductServiceTest {

  @Autowired
  ProductService sut;
  @MockBean
  ProductRepository mockRepository;

  @Test
  void getProduct() {
    final Product expected = ModelFixture.aProduct();

    Mockito
        .when(mockRepository.findById(anyLong()))
        .thenReturn(Optional.of(expected));

    final Product actual = sut.getProduct(DEFAULT_ID);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void updateProduct() {
    final Product expected = ModelFixture.aProduct();

    Mockito
        .when(mockRepository.findById(anyLong()))
        .thenReturn(Optional.of(expected));
    Mockito
        .when(mockRepository.save(any(Product.class)))
        .thenReturn(expected);

    final ProductDto dto = DtoFixture.aProductDtoOf(DEFAULT_ID);
    dto.setName("changed");
    dto.setStock(2);

    assertDoesNotThrow(() -> {
      sut.updateProduct(DEFAULT_ID, dto);
    });
  }
}
