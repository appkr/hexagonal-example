package com.example.hexagonal.adapter.out;

import com.example.hexagonal.port.out.MessagePort;
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
