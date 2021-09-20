package com.panthers.bank.model;

public class ATM {
    public synchronized String attendClient(Client client) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            int transactionNumber = client.getTransactionNumber();
            if (transactionNumber > 5) {
                client.setTransactionNumber(transactionNumber - 5);
                return "Client" + " " + client.getId() + " " + "attended successfully" + "." + " "
                        + client.getTransactionNumber() + " " + "transactions left" + ".";
            } else {
                client.setTransactionNumber(0);
                return "Client" + " " + client.getId() + " " + "attended successfully" + "." + " "
                        + "No transactions left" + ".";
            }
        }
    }
}
