package com.panthers.bank.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class BankAttentionQueue extends LinkedList<Client> {
   private final static ATM atm = new ATM();
   private final ArrayList<Client> attendedClients = new ArrayList<>();

   public List<Client> getAttendedClients() {
      return attendedClients;
   }

   public synchronized void enqueueClient(Client client) {
      new Thread(() -> this.add(client)).start();
   }

   public void attendClient(Consumer<String> print, Consumer<List<Client>> update) {
      new Thread(() -> {
         Client client = this.poll();
         print.accept(atm.attendClient(client));
         if (client.doHaveTransactions()) enqueueClient(client);
         else attendedClients.add(client);
         update.accept(this);
      }).start();
   }

}
