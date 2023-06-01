package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.models.entities.user.User;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EmployeesController {

  @FXML
  private TableView<User> clientsTable;

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private TableColumn<User, String> emailColumn;

  @FXML
  private TableColumn<User, String> nameColumn;

  @FXML
  private TableColumn<User, String> statusColumn;

  @FXML
  private FontAwesomeIconView searchButton;

  @FXML
  private TextField searchField;

  @FXML
  void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }

  @FXML
  void searchEmployee() {

  }
}
