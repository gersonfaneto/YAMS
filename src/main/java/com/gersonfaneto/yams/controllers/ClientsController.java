package com.gersonfaneto.yams.controllers;

import java.io.IOException;

import com.gersonfaneto.yams.App;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ClientsController {
  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private TextField adressField;

  @FXML
  private Button cancelButton;

  @FXML
  private Button confirmButton;

  @FXML
  private TextField nameField;

  @FXML
  private TextField phoneField;

  @FXML
  private TableView<?> clientsTable;

  @FXML
  private Button regiserButton;

  @FXML
  private FontAwesomeIconView searchButton;

  @FXML
  private TextField searchField;

  public static BorderPane mainWindow;


  @FXML
  public void initialize() {}

  @FXML
  public void closeWindow() {
    System.exit(0);
  }

  @FXML
  void openRegister() throws IOException {
    Parent clientRegisterElements = FXMLLoader.load(App.class.getResource("views/clients_register.fxml"));

    mainWindow.setRight(clientRegisterElements);
  }

  @FXML
  void searchClient() {

  }

  @FXML
  void cancelRegister() throws IOException {
    Parent clientsPaneElements = FXMLLoader.load(App.class.getResource("views/clients.fxml"));

    mainWindow.setRight(clientsPaneElements);
  }

  @FXML
  void confirmRegister() {

  }
}
