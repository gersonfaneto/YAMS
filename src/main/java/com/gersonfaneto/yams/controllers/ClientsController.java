package com.gersonfaneto.yams.controllers;

import java.io.IOException;
import java.util.List;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.client.Client;

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
import javafx.scene.control.Button;
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

public class ClientsController {
  @FXML
  private TableView<Client> clientsTable;

  @FXML
  private TableColumn<Client, String> nameColumn;

  @FXML
  private TableColumn<Client, String> addressColumn;

  @FXML
  private TableColumn<Client, String> phoneColumn;

  @FXML
  private TableColumn<Client, String> editColumn;

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

    nameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
    addressColumn.setCellValueFactory(new PropertyValueFactory<>("homeAddress"));
    phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

    Callback<TableColumn<Client, String>, TableCell<Client, String>> cellFoctory = (TableColumn<Client, String> param) -> {
      final TableCell<Client, String> cell = new TableCell<Client, String>() {
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
              Client selectedClient = clientsTable.getSelectionModel().getSelectedItem();

              DAO.fromClients().deleteByID(selectedClient.getClientID());

              refreshTable();
            });

            editIcon.setOnMouseClicked((MouseEvent event) -> {
              Client selectedClient = clientsTable.getSelectionModel().getSelectedItem();

              FXMLLoader loaderFXML = new FXMLLoader();
              loaderFXML.setLocation(App.class.getResource("views/clients_update.fxml"));

              try {
                loaderFXML.load();
              } catch (IOException e) {
                e.printStackTrace();
              }

              ClientsUpdateController updateController = loaderFXML.getController();

              updateController.injectFields(
                selectedClient.getClientID(),
                selectedClient.getClientName(),
                selectedClient.getHomeAddress(),
                selectedClient.getPhoneNumber()
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
  public void registerClient() throws IOException {
    Parent clientRegisterView = FXMLLoader.load(App.class.getResource("views/clients_register.fxml"));

    MainController.mainWindow.setRight(clientRegisterView);
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }

  public void refreshTable() {
    clientsList.clear();
    clientsList.addAll(DAO.fromClients().findMany());
  }
}
