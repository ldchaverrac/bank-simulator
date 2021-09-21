package com.panthers.bank.model;

public class ATM {

   public synchronized String attendClient(Client client) {
      String message = "Client" + " " + client.getId() + " " + "attended successfully" + "." + " ";
      try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
      int transactionNumber = client.getTransactionNumber();
      if (transactionNumber > 5) {
         client.setTransactionNumber(transactionNumber - 5);
         message += client.getTransactionNumber() + " " + "transactions left" + ".";
      } else {
         client.setTransactionNumber(0);
         message += "No transactions left" + ".";
      }
      return message;

   }
}
