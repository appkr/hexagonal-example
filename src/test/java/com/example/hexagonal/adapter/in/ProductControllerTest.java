package com.example.hexagonal.adapter.in;

import static com.example.hexagonal.domain.ModelFixture.DEFAULT_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.hexagonal.port.dto.DtoFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class ProductControllerTest {

  static ObjectMapper mapper = new ObjectMapper(); // Don't do in production; Declare a bean

  MockMvc mvc;

  @Test
  void getProduct() throws Exception {
    mvc
        .perform(get("/api/products/{productId}", DEFAULT_ID)
            .accept("application/json")
        )
        .andDo(print())
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  void updateProduct() throws Exception {
    final byte[] content = mapper.writeValueAsBytes(DtoFixture.aProductDtoOf(DEFAULT_ID));

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
        .standaloneSetup(new ProductController())
        .build();
  }
}