package io.pivotal.balance.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(TransactionProcessor.class)
public class BalanceApplication {

  public static final Logger log = LoggerFactory.getLogger(BalanceApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(BalanceApplication.class, args);
    log.info("The Balance Application has started...");
  }
}
