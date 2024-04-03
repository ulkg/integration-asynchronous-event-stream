package io.pivotal.money.laundering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(TransactionProcessor.class)
public class MoneyLaunderingApplication {

  public static final Logger log = LoggerFactory.getLogger(MoneyLaunderingApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(MoneyLaunderingApplication.class, args);
    log.info("The MoneyLaunderingApplication Application has started...");
  }
}
