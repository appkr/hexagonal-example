package com.example.hexagonal.application.port.outbound;

public interface MessagePort {

  void send(Object aHypotheticalMessage);
}
