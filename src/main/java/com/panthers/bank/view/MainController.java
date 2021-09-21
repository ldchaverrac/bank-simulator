package com.panthers.bank.view;

import com.panthers.bank.model.BankAttentionQueue;
import com.panthers.bank.model.Client;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MainController {
   @FXML private TableView<Client> clientsAttendedTable;
   @FXML private TableView<Client> clientsWaitingTable;
   private final BankAttentionQueue bankQueue = new BankAttentionQueue();

   private void updateTables() {
      clientsWaitingTable.setItems(FXCollections.observableList(bankQueue));
      clientsAttendedTable.setItems(FXCollections.observableList(bankQueue.getAttendedClients()));
   }

   //Añade un cliente a la cola y devuelve esta ultima
   private List<Client> AddClient() {
      Client client = new Client();
      bankQueue.enqueueClient(client);
      return bankQueue;
   }

   @FXML protected void onAttendButtonClick() {
      CompletableFuture.supplyAsync(() -> {
         String result = bankQueue.attendClient();
         updateTables();
         return result;
      }).thenAccept(System.out::println);
   }

   @FXML public void onAddClientButtonClick() {
      CompletableFuture.supplyAsync(this::AddClient)
            .thenAccept((list) -> clientsWaitingTable.setItems(FXCollections.observableList(list)));
   }

}