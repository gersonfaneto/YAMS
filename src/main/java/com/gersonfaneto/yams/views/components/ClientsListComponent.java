package com.gersonfaneto.yams.views.components;

import java.io.IOException;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.controllers.ClientsUpdateController;
import com.gersonfaneto.yams.controllers.MainController;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.client.Client;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClientsListComponent extends AnchorPane {
  private Client targetClient;
  private ObservableList<Client> clientsList;

  public ClientsListComponent(Client targetClient, ObservableList<Client> clientsList) {
    this.targetClient = targetClient;
    this.clientsList = clientsList;

    super.getStylesheets().add(App.class.getResource("stylesheets/global.css").toExternalForm());
    super.getStylesheets().clear();
    super.getStyleClass().add("client-item");

    super.setMinSize(650, 100);
    super.setPrefSize(650, 100);
    super.setMaxSize(650, 100);

    FontAwesomeIconView userIcon = new FontAwesomeIconView(FontAwesomeIcon.USER);
    FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
    FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

    userIcon.setLayoutX(27);
    userIcon.setLayoutY(67);
    userIcon.setSize("50");

    Button editButton = new Button();

    editButton.setLayoutX(610);
    editButton.setLayoutY(10);
    editButton.getStyleClass().add("transparent-button");

    editIcon.setSize("20");
    editIcon.getStyleClass().add("edit-icon");

    editButton.setGraphic(editIcon);

    Button deleteButton = new Button();

    deleteButton.setLayoutX(610);
    deleteButton.setLayoutY(50);
    deleteButton.getStyleClass().add("transparent-button");

    deleteIcon.setSize("20");
    deleteIcon.getStyleClass().add("delete-icon");

    deleteButton.setGraphic(deleteIcon);

    deleteButton.setOnMouseClicked((MouseEvent event) -> {
      deleteClient();
    });

    editButton.setOnMouseClicked((MouseEvent event) -> {
      updateClient();
    });

    Label nameField = new Label(targetClient.getClientName());
    Label addressField = new Label(targetClient.getHomeAddress());
    Label phoneNumberField = new Label(targetClient.getPhoneNumber());
    
    nameField.setLayoutX(100);
    nameField.setLayoutY(13);
    nameField.setMinSize(450, 20);
    nameField.setPrefSize(450, 20);
    nameField.setMaxSize(450, 20);

    addressField.setLayoutX(100);
    addressField.setLayoutY(38);
    addressField.setMinSize(450, 20);
    addressField.setPrefSize(450, 20);
    addressField.setMaxSize(450, 20);

    phoneNumberField.setLayoutX(100);
    phoneNumberField.setLayoutY(64);
    phoneNumberField.setMinSize(450, 20);
    phoneNumberField.setPrefSize(450, 20);
    phoneNumberField.setMaxSize(450, 20);

    super.getChildren().addAll(userIcon, editButton, deleteButton);
    super.getChildren().addAll(nameField, addressField, phoneNumberField);
  }

  private void deleteClient() {
    String confirmationMessage = "Deseja excluir o cadastro de %s?".formatted(targetClient.getClientName());

    ActionConfirmationDialog confirmDialog = new ActionConfirmationDialog(confirmationMessage);

    Stage modalStage = new Stage();

    MainController.modalStage = modalStage;

    modalStage.setScene(new Scene(confirmDialog));
    modalStage.initStyle(StageStyle.UNDECORATED);
    modalStage.initModality(Modality.APPLICATION_MODAL);
    modalStage.initOwner(MainController.primaryStage);
    modalStage.showAndWait();

    if (MainController.isConfirmed) {
      DAO.fromClients().deleteByID(targetClient.getClientID());
      clientsList.remove(targetClient);
    }
  }

  private void updateClient() {
    FXMLLoader loaderFXML = new FXMLLoader();
    loaderFXML.setLocation(App.class.getResource("views/clients_update.fxml"));

    try {
      loaderFXML.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    ClientsUpdateController updateController = loaderFXML.getController();

    updateController.injectFields(
      targetClient.getClientID(),
      targetClient.getClientName(),
      targetClient.getHomeAddress(),
      targetClient.getPhoneNumber()
    );

    Parent updateView = loaderFXML.getRoot();
    Stage modalStage = new Stage();

    modalStage.setScene(new Scene(updateView));
    modalStage.initStyle(StageStyle.UNDECORATED);
    modalStage.initModality(Modality.APPLICATION_MODAL);
    modalStage.initOwner(MainController.primaryStage);
    modalStage.show();

    MainController.modalStage = modalStage;
  }
}
