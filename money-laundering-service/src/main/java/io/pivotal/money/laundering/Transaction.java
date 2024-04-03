package io.pivotal.money.laundering;

public class Transaction {
    private String transactionId;
    private String recipientId;
    private String senderId;
    private long amount;
    private String status;

    public Transaction() {

    }

    public Transaction(String transactionId, String recipientId, String senderId, long amount) {
        this.transactionId = transactionId;
        this.recipientId = recipientId;
        this.senderId = senderId;
        this.amount = amount;
        this.setStatus(TransactionStatus.PENDING.name());
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
