package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;
import com.gersonfaneto.yams.utils.TypeParser;
import com.gersonfaneto.yams.views.components.UsersListComponent;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class EmployeesController {

  @FXML
  private ListView<User> listView;

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

    listView.setCellFactory(listView -> new ListCell<User>() {
      @Override
      protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);

        if (user == null || empty) {
          setGraphic(null);
        } else {
          UsersListComponent clientComponent = new UsersListComponent(user, employeesList);

          setGraphic(clientComponent);
        }
      }
    });

    roleFilter.getItems().add("Todos");
    for (UserType userType : UserType.values()) {
      if (userType != UserType.Administrator) {
        roleFilter.getItems().add(TypeParser.parseUserType(userType));
      }
    }

    List<User> allEmployees = DAO.fromUsers().findMany()
        .stream()
        .filter(user -> user.getUserType() != UserType.Administrator)
        .toList();

    employeesList.addAll(allEmployees);

    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredEmployees.setPredicate(user -> {
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if (user.getUserName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
          return true;
        } else {
          return false;
        }
      });
    });

    SortedList<User> sortedEmployees = new SortedList<>(filteredEmployees);

    listView.setItems(sortedEmployees);
  }

  @FXML
  public void filterSearch() {
    listView.setItems(filteredEmployees.filtered(user -> {
      String roleValue = roleFilter.getValue();

      UserType userType = TypeParser.parseUserType(roleValue);

      return userType == null || user.getUserType() == userType;
    }));
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }
}
