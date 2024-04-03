package io.pivotal.balance.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface TransactionProcessor {

  String TRANSACTIONS_IN = "transactionOutput";
  String MONEY_LAUNDERING_IN = "moneyLaunderingOutput";

  @Input(TRANSACTIONS_IN)
  SubscribableChannel sourceOfTransaction();

  @Input(MONEY_LAUNDERING_IN)
  SubscribableChannel sourceOfMoneyLaundering();
}
