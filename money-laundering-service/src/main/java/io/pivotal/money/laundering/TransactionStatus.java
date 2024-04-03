package io.pivotal.money.laundering;


/**
 * Definition of the valid states for a transaction
 */
public enum TransactionStatus {
  MONEY_LAUNDERING_RISK, APPROVED, DECLINED, PENDING, REJECTED
}
