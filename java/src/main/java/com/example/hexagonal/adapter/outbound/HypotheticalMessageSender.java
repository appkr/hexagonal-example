package com.example.hexagonal.adapter.outbound;

import com.example.hexagonal.application.port.outbound.MessagePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HypotheticalMessageSender implements MessagePort {

  @Override
  public void send(Object aHypotheticalMessage) {
    log.info("Send a message: {}", aHypotheticalMessage);
  }
}
