package com.gersonfaneto.yams.controllers;

import java.io.IOException;
import java.util.List;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;
import com.gersonfaneto.yams.utils.TypeParser;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

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
  private TableColumn<User, String> editColumn;

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

    Callback<TableColumn<User, String>, TableCell<User, String>> cellFoctory = (TableColumn<User, String> param) -> {
      final TableCell<User, String> cell = new TableCell<User, String>() {
        @Override
        public void updateItem(String item, boolean empty) {
          super.updateItem(item, empty);

          if (empty) {
            setGraphic(null);
            setText(null);
          }
          else {
            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

            deleteIcon.getStyleClass().clear();
            deleteIcon.setStyleClass("delete_icon");

            editIcon.getStyleClass().clear();
            editIcon.setStyleClass("edit_icon");

            deleteIcon.setOnMouseClicked((MouseEvent event) -> {
              User selectedUser = employeesTable.getSelectionModel().getSelectedItem();

              DAO.fromUsers().deleteByID(selectedUser.getUserID());

              refreshTable();
            });

            editIcon.setOnMouseClicked((MouseEvent event) -> {
              User selectedUser = employeesTable.getSelectionModel().getSelectedItem();

              FXMLLoader loaderFXML = new FXMLLoader();
              loaderFXML.setLocation(App.class.getResource("views/employees_update.fxml"));

              try {
                loaderFXML.load();
              } catch (IOException e) {
                e.printStackTrace();
              }

              EmployeesUpdateController updateController = loaderFXML.getController();

              updateController.injectFields(
                selectedUser.getUserID(),
                selectedUser.getUserName(),
                selectedUser.getUserEmail(),
                selectedUser.getUserPassword(),
                selectedUser.getUserType()
              );

              Parent updateView = loaderFXML.getRoot();
              Stage modalStage = new Stage();

              modalStage.setScene(new Scene(updateView));
              modalStage.initStyle(StageStyle.UNDECORATED);
              modalStage.show();

              MainController.modalWindow = modalStage;

            });

            HBox managebtn = new HBox(editIcon, deleteIcon);
            managebtn.setStyle("-fx-alignment:center");
            managebtn.setSpacing(5);
            HBox.setMargin(deleteIcon, new Insets(2, 2, 2, 5));
            HBox.setMargin(editIcon, new Insets(2, 5, 2, 2));

            setGraphic(managebtn);

            setText(null);
          }
        }
      };

      return cell;
    };

    editColumn.setCellFactory(cellFoctory);

    roleFilter.getItems().add("Todos");
    roleFilter.getItems().add("Têcnico(a)");
    roleFilter.getItems().add("Recepcionista");

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

      UserType userType = TypeParser.parseUserType(roleValue);

      return userType == null || user.getUserType() == userType;
    }));
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }

  public void refreshTable() {
    employeesList.clear();
    employeesList.addAll(DAO.fromUsers().findMany());
  }
}
