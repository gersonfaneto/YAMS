package com.gersonfaneto.yams.controllers;

import java.io.IOException;
import java.util.List;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.client.Client;
import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.views.components.ClientsListComponent;
import com.gersonfaneto.yams.views.components.ComponentSize;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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

public class CreateOrderController {

  @FXML
  private FontAwesomeIconView backButton;

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private Button addService;

  @FXML
  private TextField searchField;

  @FXML
  private ListView<Client> clientsListView;

  @FXML
  private ListView<Service> servicesListView;

  @FXML
  private Button confirmButton;

  @FXML
  private Button cancelButton;

  private ObservableList<Client> clientsList;
  private FilteredList<Client> filteredClients;

  @FXML
  public void initialize() {
    clientsList = FXCollections.observableArrayList();
    filteredClients = new FilteredList<>(clientsList, x -> true);

    clientsListView.setCellFactory(listView -> new ListCell<Client>() {
      @Override
      protected void updateItem(Client client, boolean empty) {
        super.updateItem(client, empty);

        if (client == null || empty) {
          setGraphic(null);
        } else {
          ClientsListComponent clientComponent = new ClientsListComponent(
              client,
              clientsList,
              ComponentSize.Small
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

    clientsListView.setItems(sortedClients);
  }

  @FXML
  public void cancelRegister() throws IOException {
    Parent servicesView = FXMLLoader.load(App.class.getResource("views/services.fxml"));

    MainController.mainWindow.setRight(servicesView);
  }

  @FXML
  public void confirmRegister() {

  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }
}
