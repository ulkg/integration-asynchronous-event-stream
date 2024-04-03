package io.pivotal.customer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface CustomerProcessor {

    String APPLICATIONS_IN = "customerOutput";

    @Input(APPLICATIONS_IN)
    SubscribableChannel sourceOfLoanApplications();

}
