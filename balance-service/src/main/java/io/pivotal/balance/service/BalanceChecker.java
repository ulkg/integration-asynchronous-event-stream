package io.pivotal.balance.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class BalanceChecker {

    public static final Logger log = LoggerFactory.getLogger(BalanceChecker.class);
    private TransactionProcessor processor;

    @Autowired
    public BalanceChecker(TransactionProcessor processor) {
        this.processor = processor;
    }

    @StreamListener(TransactionProcessor.TRANSACTIONS_IN)
    public void checkTransactions(Transaction transaction) {
        log.info("received transaction: transaction id {}, recipient id {}, sender id {}, amount {} €", transaction.getTransactionId(), transaction.getRecipientId(), transaction.getSenderId(), transaction.getAmount());

        if (transaction.getAmount() == 0) {
            transaction.setStatus(TransactionStatus.DECLINED.name());
        }
        transaction.setStatus(TransactionStatus.APPROVED.name());
    }

    @StreamListener(TransactionProcessor.MONEY_LAUNDERING_IN)
    public void processMoneyLaunderingAttempts(Transaction transaction) {
        log.info("money laundering attempt: received transaction: transaction id {}, recipient id {}, sender id {}, amount {} €", transaction.getTransactionId(), transaction.getRecipientId(), transaction.getSenderId(), transaction.getAmount());

        transaction.setStatus(TransactionStatus.MONEY_LAUNDERING_RISK.name());
        log.info("Resetting status of transaction {} to money-laundering-risk", transaction.getTransactionId());
    }
}
