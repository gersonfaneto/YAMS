package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.user.User;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeesController {

  @FXML
  private TableView<User> employeesTable;

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private TableColumn<User, String> emailColumn;

  @FXML
  private TableColumn<User, String> nameColumn;

  @FXML
  private TableColumn<User, String> roleColumn;

  @FXML
  private FontAwesomeIconView searchButton;

  @FXML
  private TextField searchField;

  @FXML
  public void initialize() {
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
    roleColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));

    employeesTable.setItems(FXCollections.observableArrayList(DAO.fromUsers().findMany()));
  }

  @FXML
  void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }

  @FXML
  void searchEmployee() {

  }
}
