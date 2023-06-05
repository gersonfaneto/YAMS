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
  private ComboBox<String> roleFilter;

  @FXML
  private TextField searchField;

  private final ObservableList<User> employeesList = FXCollections.observableArrayList();
  private final FilteredList<User> filteredEmployees = new FilteredList<>(employeesList, x -> true);

  @FXML
  public void initialize() {
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
    // TODO: Translate on view!
    roleColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));

    roleFilter.getItems().add("Todos");
    roleFilter.getItems().add("Têcnico(a)");
    roleFilter.getItems().add("Recepcionista");

    List<User> allEmployees = DAO.fromUsers().findMany()
      .stream()
      .filter(x -> !x.getUserEmail().equals("admin"))
      .toList();

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