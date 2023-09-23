package com.example.hexagonal.domain;

public class ModelFixture {

  public static final Long DEFAULT_ID = 1L;
  public static final String DEFAULT_NAME = "Foo";
  public static final Integer DEFAULT_STOCK = 1;

  public static Product aProduct() {
    final Product product = new Product();
    product.setId(DEFAULT_ID);
    product.setName(DEFAULT_NAME);
    product.setStock(DEFAULT_STOCK);

    return product;
  }
}
