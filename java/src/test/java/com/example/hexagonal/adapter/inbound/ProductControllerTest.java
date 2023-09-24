package com.example.hexagonal.adapter.inbound;

import static com.example.hexagonal.application.domain.ModelFixture.DEFAULT_ID;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.hexagonal.application.domain.ModelFixture;
import com.example.hexagonal.application.port.dto.DtoFixture;
import com.example.hexagonal.application.port.dto.DtoMapper;
import com.example.hexagonal.application.port.dto.ProductDto;
import com.example.hexagonal.application.port.inbound.ProductPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class ProductControllerTest {

  static ObjectMapper mapper = new ObjectMapper(); // Don't do in production; Declare a bean

  MockMvc mvc;

  @MockBean ProductPort mockUsecase;
  @Autowired DtoMapper dtoMapper;

  @Test
  void getProduct() throws Exception {
    Mockito
        .when(mockUsecase.getProduct(anyLong()))
        .thenReturn(ModelFixture.aProduct());

    mvc
        .perform(get("/api/products/{productId}", DEFAULT_ID)
            .accept("application/json")
        )
        .andDo(print())
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  void updateProduct() throws Exception {
    final ProductDto dto = DtoFixture.aProductDtoOf(DEFAULT_ID);

    Mockito
        .when(mockUsecase.updateProduct(DEFAULT_ID, dto))
        .thenReturn(ModelFixture.aProduct());

    final byte[] content = mapper.writeValueAsBytes(dto);

    mvc
        .perform(put("/api/products/{productId}", DEFAULT_ID)
            .contentType("application/json")
            .accept("application/json")
            .content(content)
        )
        .andDo(print())
        .andExpect(status().is2xxSuccessful());
  }

  @BeforeEach
  void setup() {
    this.mvc = MockMvcBuilders
        .standaloneSetup(new ProductController(mockUsecase, dtoMapper))
        .build();
  }
}
