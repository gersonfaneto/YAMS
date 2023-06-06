package com.gersonfaneto.yams.controllers;

import java.util.List;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeesController {

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private TableView<User> employeesTable;

  @FXML
  private TableColumn<User, String> emailColumn;

  @FXML
  private TableColumn<User, String> nameColumn;

  @FXML
  private ComboBox<String> roleFilter;

  @FXML
  private TextField searchField;

  private ObservableList<User> employeesList;
  private FilteredList<User> filteredEmployees;

  @FXML
  public void initialize() {
    employeesList = FXCollections.observableArrayList();
    filteredEmployees = new FilteredList<>(employeesList, x -> true);

    emailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));

    roleFilter.getItems().add("Todos");
    roleFilter.getItems().add("Têcnico(a)");
    roleFilter.getItems().add("Recepcionista");
    roleFilter.getItems().add("Administrador");

    List<User> allEmployees = DAO.fromUsers().findMany();

    employeesList.addAll(allEmployees);

    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredEmployees.setPredicate(user -> {
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if (user.getUserName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
          return true;
        }
        else if (user.getUserEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
          return true;
        }
        else {
          return false;
        }
      });
    });

    SortedList<User> sortedEmployees = new SortedList<>(filteredEmployees);

    sortedEmployees.comparatorProperty().bind(employeesTable.comparatorProperty());

    employeesTable.setItems(sortedEmployees);
  }

  @FXML
  public void filterSearch() {
    employeesTable.setItems(filteredEmployees.filtered(user -> {
      String roleValue = roleFilter.getValue();
      UserType userType = null;

      switch (roleValue) {
        case "Recepcionista":
          userType = UserType.Receptionist;
          break;
        case "Têcnico(a)":
          userType = UserType.Technician;
          break;
        case "Administrador":
          userType = UserType.Administrator;
        default:
          break;
      }

      return userType == null || user.getUserType() == userType;
    }));
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }
}
