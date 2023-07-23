package com.example.hexagonal.port.out;

import com.example.hexagonal.domain.Product;

public interface MessagePort {

  void send(Object aHypotheticalMessage);
}
