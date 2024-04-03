package io.pivotal.money.laundering;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface TransactionProcessor {

  String TRANSACTIONS_IN = "transactionOutput";
  String MONEY_LAUNDERING_OUT = "moneyLaunderingOutput";

  String APPROVED_OUT = "approved";
  String DECLINED_OUT = "declined";

  @Input(TRANSACTIONS_IN)
  SubscribableChannel sourceOfTransaction();

  @Output(MONEY_LAUNDERING_OUT)
  MessageChannel launderingAttempt();
}
