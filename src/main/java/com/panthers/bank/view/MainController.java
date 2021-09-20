package com.panthers.bank.view;

import com.panthers.bank.model.BankAttentionQueue;
import com.panthers.bank.model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.function.Consumer;
import java.util.function.Function;

public class MainController {
   @FXML private Label infoText;
   @FXML private TableView<Client> clientsAttendedTable;
   @FXML private TableView<Client> clientsWaitingTable;
   private BankAttentionQueue queue = new BankAttentionQueue();

   @FXML protected void onAttendButtonClick() {
      queue.attendClient(System.out::println, (clients) -> {
         clientsWaitingTable.setItems(FXCollections.observableList(queue));
         clientsAttendedTable.setItems(FXCollections.observableList(queue.getAttendedClients()));
      });
   }

   @FXML public void onAddClientButtonClick() {
      Client client = new Client();
      queue.enqueueClient(client);
      clientsWaitingTable.setItems(FXCollections.observableList(queue));
   }

}