package com.gersonfaneto.yams.controllers;

import java.io.IOException;
import java.util.List;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.client.Client;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientsController {
  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private Button cancelButton;

  @FXML
  private Button confirmButton;

  @FXML
  private Label visualFeedback;

  @FXML
  private TextField nameField;

  @FXML
  private TextField addressField;

  @FXML
  private TextField phoneField;

  @FXML
  private TableView<Client> clientsTable;

  @FXML
  private Button registerButton;

  @FXML
  private FontAwesomeIconView searchButton;

  @FXML
  private TextField searchField;

  @FXML
  private TableColumn<Client, String> nameColumn;

  @FXML
  private TableColumn<Client, String> addressColumn;

  @FXML
  private TableColumn<Client, String> phoneColumn;

  private final ObservableList<Client> clientsList = FXCollections.observableArrayList();
  private final FilteredList<Client> filteredClients = new FilteredList<>(clientsList, x -> true);

  @FXML
  public void initialize() {
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
    addressColumn.setCellValueFactory(new PropertyValueFactory<>("homeAddress"));
    phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

    List<Client> allClients = DAO.fromClients().findMany();

    clientsList.addAll(allClients);

    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredClients.setPredicate(user -> {
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if (user.getClientName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
          return true;
        }
        else if (user.getHomeAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
          return true;
        }
        else if (user.getPhoneNumber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
          return true;
        }
        else {
          return false;
        }
      });
    });

    SortedList<Client> sortedClients  = new SortedList<>(filteredClients);

    sortedClients.comparatorProperty().bind(clientsTable.comparatorProperty());

    clientsTable.setItems(sortedClients);
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }

  @FXML
  public void openRegister() throws IOException {
    Parent clientRegisterElements = FXMLLoader.load(App.class.getResource("views/clients_register.fxml"));

    MainController.mainWindow.setRight(clientRegisterElements);
  }

  @FXML
  public void cancelRegister() throws IOException {
    Parent clientsPaneElements = FXMLLoader.load(App.class.getResource("views/clients.fxml"));

    MainController.mainWindow.setRight(clientsPaneElements);
  }

  @FXML
  public void confirmRegister() {

  }
}
