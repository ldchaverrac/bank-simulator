package com.panthers.bank.view;

import com.panthers.bank.model.BankAttentionQueue;
import com.panthers.bank.model.Client;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TableView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class MainController {
   @FXML private TableView<Client> clientsAttendedTable;
   @FXML private TableView<Client> clientsWaitingTable;
   private final BankAttentionQueue bankQueue = new BankAttentionQueue();
   
   private final Consumer<List<Client>> updateTables = (clients) -> {
      clientsWaitingTable.setItems(FXCollections.observableList(bankQueue));
      clientsAttendedTable.setItems(FXCollections.observableList(bankQueue.getAttendedClients()));
   };

   //Añade un cliente a la cola y devuelve esta ultima
   private List<Client> AddClient() {
      bankQueue.enqueueClient(new Client());
      return bankQueue;
   }

   @FXML protected void onAttendButtonClick() {
      try {
         CompletableFuture<String> future;
         future = CompletableFuture.supplyAsync(() -> bankQueue.attendClient(updateTables));
         Notifications notificationBuilder = Notifications.create()
               .title("Resultado")
               .text(future.get())
               .hideAfter(Duration.seconds(2))
               .position(Pos.CENTER);
         notificationBuilder.showInformation();
      } catch (InterruptedException | ExecutionException e) {
         e.printStackTrace();
      }
   }

   @FXML public void onAddClientButtonClick() {
      try {
         CompletableFuture<List<Client>> future = CompletableFuture.supplyAsync(this::AddClient);
         clientsWaitingTable.setItems(FXCollections.observableList(future.get()));
      } catch (InterruptedException | ExecutionException e) {
         e.printStackTrace();
      }
   }

}