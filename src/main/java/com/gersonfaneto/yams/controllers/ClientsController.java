package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.client.Client;
import com.gersonfaneto.yams.views.components.ClientsListComponent;
import com.gersonfaneto.yams.views.components.ComponentSize;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ClientsController {

  @FXML
  private ListView<Client> listView;

  @FXML
  private Button registerButton;

  @FXML
  private TextField searchField;

  private ObservableList<Client> clientsList;
  private FilteredList<Client> filteredClients;

  @FXML
  public void initialize() {
    clientsList = FXCollections.observableArrayList();
    filteredClients = new FilteredList<>(clientsList, x -> true);

    listView.setCellFactory(listView -> new ListCell<Client>() {
      @Override
      protected void updateItem(Client client, boolean empty) {
        super.updateItem(client, empty);

        if (client == null || empty) {
          setGraphic(null);
        } else {
          ClientsListComponent clientComponent = new ClientsListComponent(
              client,
              clientsList,
              ComponentSize.Medium
          );

          setGraphic(clientComponent);
        }
      }
    });

    List<Client> allClients = DAO.fromClients().findMany();

    clientsList.addAll(allClients);

    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredClients.setPredicate(user -> {
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if (user.getClientName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
          return true;
        } else {
          return false;
        }
      });
    });

    SortedList<Client> sortedClients = new SortedList<>(filteredClients);

    listView.setItems(sortedClients);
  }

  @FXML
  public void registerClient() throws IOException {
    Parent clientRegisterView = FXMLLoader.load(
        App.class.getResource("views/clients_register.fxml")
    );

    MainController.mainWindow.setRight(clientRegisterView);
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }
}
