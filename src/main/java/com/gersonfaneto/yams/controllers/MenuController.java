package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.models.entities.user.UserType;
import com.gersonfaneto.yams.views.components.ActionConfirmationDialog;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MenuController {

  @FXML
  private FontAwesomeIconView signOutButton;

  @FXML
  private FontAwesomeIconView homeIcon;

  @FXML
  private Button homeButton;

  @FXML
  private FontAwesomeIconView employeesIcon;

  @FXML
  private Button employeesButton;

  @FXML
  private Button clientsButton;

  @FXML
  private Button servicesButton;

  @FXML
  private Button stockButton;

  @FXML
  private Button reportsButton;

  @FXML
  private AnchorPane sideBar;

  private Button activeButton;

  public void initialize() {
    if (MainController.loggedUser.getUserType() == UserType.Administrator) {
      homeButton.setVisible(false);
      homeIcon.setVisible(false);
      setActiveButton(employeesButton);
    } else if (MainController.loggedUser.getUserType() == UserType.Receptionist) {
      homeButton.setVisible(false);
      homeIcon.setVisible(false);
      employeesButton.setVisible(false);
      employeesIcon.setVisible(false);
      setActiveButton(clientsButton);
    } else {
      employeesButton.setVisible(false);
      employeesIcon.setVisible(false);
      setActiveButton(homeButton);
    }

    for (Node currentNode : sideBar.getChildren()) {
      if (currentNode instanceof Button currentButton) {
        currentButton.setOnMouseClicked(event -> setActiveButton(currentButton));
      }
    }
  }

  @FXML
  public void handleClicks(ActionEvent actionEvent) throws IOException {
    Parent targetView = null;

    if (actionEvent.getSource() == homeButton) {
      targetView = FXMLLoader.load(App.class.getResource("views/home.fxml"));
    } else if (actionEvent.getSource() == clientsButton) {
      targetView = FXMLLoader.load(App.class.getResource("views/clients.fxml"));
    } else if (actionEvent.getSource() == servicesButton) {
      targetView = FXMLLoader.load(App.class.getResource("views/services.fxml"));
    } else if (actionEvent.getSource() == stockButton) {
      targetView = FXMLLoader.load(App.class.getResource("views/stock.fxml"));
    } else if (actionEvent.getSource() == reportsButton) {
      targetView = FXMLLoader.load(App.class.getResource("views/reports.fxml"));
    } else if (actionEvent.getSource() == employeesButton) {
      targetView = FXMLLoader.load(App.class.getResource("views/employees.fxml"));
    }

    MainController.mainWindow.setRight(targetView);
  }

  @FXML
  public void signOut() throws IOException {
    String confirmationMessage = "Deseja mesmo sair?";

    ActionConfirmationDialog confirmDialog = new ActionConfirmationDialog(confirmationMessage);

    Stage modalStage = new Stage();

    MainController.modalStage = modalStage;

    modalStage.setScene(new Scene(confirmDialog));
    modalStage.initStyle(StageStyle.UNDECORATED);
    modalStage.initModality(Modality.APPLICATION_MODAL);
    modalStage.initOwner(MainController.primaryStage);
    modalStage.showAndWait();

    if (MainController.isConfirmed) {
      MainController.saveData();

      Parent loginView = FXMLLoader.load(App.class.getResource("views/login.fxml"));

      MainController.mainWindow.getChildren().setAll(loginView);
    }
  }

  private void setActiveButton(Button sourceButton) {
    if (this.activeButton != null) {
      this.activeButton.getStyleClass().removeAll("active");
    }

    sourceButton.getStyleClass().add("active");
    this.activeButton = sourceButton;
  }
}
