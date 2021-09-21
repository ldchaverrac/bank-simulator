package com.panthers.bank.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BankAttentionQueue extends LinkedList<Client> {
   private final static ATM atm = new ATM();
   private final ArrayList<Client> attendedClients = new ArrayList<>();

   public List<Client> getAttendedClients() {
      return attendedClients;
   }

   public void enqueueClient(Client client) {
      this.add(client);
   }

   public synchronized String attendClient() {
      Client client = this.poll();
      String message = atm.attendClient(client);
      if (client.doHaveTransactions()) enqueueClient(client);
      else attendedClients.add(client);
      return message;
   }
}
