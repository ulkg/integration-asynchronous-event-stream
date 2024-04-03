package io.pivotal.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(CustomerProcessor.class)
public class CustomerListerApplication {

  public static final Logger log = LoggerFactory.getLogger(CustomerListerApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(CustomerListerApplication.class, args);
    log.info("The CustomerLister Application has started...");
  }
}
