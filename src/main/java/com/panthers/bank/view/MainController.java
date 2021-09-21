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
      Client client = new Client();
      bankQueue.enqueueClient(client);
      return bankQueue;
   }

   @FXML protected void onAttendButtonClick() {
      CompletableFuture.supplyAsync(() -> {
         String result = bankQueue.attendClient();
         updateTables.accept(bankQueue);
         return result;
      }).thenAccept((result) -> {
         Notifications.create()
               .title("Resultado")
               .text(result)
               .hideAfter(Duration.seconds(2))
               .position(Pos.CENTER)
               .darkStyle()
               .showInformation();
      });
   }

   @FXML public void onAddClientButtonClick() {
      CompletableFuture.supplyAsync(this::AddClient)
            .thenAccept((list) -> clientsWaitingTable.setItems(FXCollections.observableList(list)));
   }

}