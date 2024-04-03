package io.pivotal.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerLister {

    public static final Logger log = LoggerFactory.getLogger(CustomerLister.class);
    private CustomerProcessor processor;

    @Autowired
    public CustomerLister(CustomerProcessor processor) {
        this.processor = processor;
    }

    @StreamListener(CustomerProcessor.APPLICATIONS_IN)
    public void listCustomers(Customer customer) {
        log.info("{}, {}, {}", customer.getId(), customer.getName(), customer.getBirthdate());
    }
}
