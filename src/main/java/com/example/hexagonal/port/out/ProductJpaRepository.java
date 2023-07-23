package com.example.hexagonal.port.out;

import com.example.hexagonal.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

}
