package com.panthers.bank.model;

import java.util.Random;

public class Client {
    public static int consecutive = 0;
    private final String id;
    private int transactionNumber;

    public Client() {
        this.id = Integer.toString(++consecutive);
        transactionNumber = new Random().nextInt(21);
    }

    public String getId() {
        return id;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public boolean doHaveTransactions() {
        return transactionNumber > 0;
    }

    @Override
    public String toString() {
        return "Client{id='" + id + '\'' + ", transactionNumber=" + transactionNumber + '}';
    }
}
