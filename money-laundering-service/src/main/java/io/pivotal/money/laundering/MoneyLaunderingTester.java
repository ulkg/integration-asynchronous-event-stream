package io.pivotal.money.laundering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MoneyLaunderingTester {

    public static final Logger log = LoggerFactory.getLogger(MoneyLaunderingTester.class);
    private TransactionProcessor processor;

    @Autowired
    public MoneyLaunderingTester(TransactionProcessor processor) {
        this.processor = processor;
    }

    @StreamListener(TransactionProcessor.TRANSACTIONS_IN)
    public void checkAndSortLoans(Transaction transaction) {
        log.info("received transaction: transaction id {}, recipient id {}, sender id {}, amount {} €", transaction.getTransactionId(), transaction.getRecipientId(), transaction.getSenderId(), transaction.getAmount());

        if (transaction.getAmount() > 10000000L) {
            log.info("money laundering attempt for transaction: transaction id {}, recipient id {}, sender id {}, amount {} €", transaction.getTransactionId(), transaction.getRecipientId(), transaction.getSenderId(), transaction.getAmount());
            processor.launderingAttempt().send(message(transaction));
        }
    }

    private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
}
