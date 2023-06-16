package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.App;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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
  private Button invoicesButton;

  @FXML
  private AnchorPane sideBar;


  private Button activeButton;

  public void initialize() {
    for (Node currentNode : sideBar.getChildren()) {
      if (currentNode instanceof Button currentButton) {
        currentButton.setOnMouseClicked(event -> setActiveButton(currentButton));
      }
    }

    switch(MainController.loggedUser.getUserType()) {
      case Administrator:
        setActiveButton(employeesButton);
        break;
      case Technician:
        setActiveButton(homeButton);
        break;
      case Receptionist:
        setActiveButton(clientsButton);
        break;
      default:
        break;
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
    } else {
      targetView = FXMLLoader.load(App.class.getResource("views/invoices.fxml"));
    }

    MainController.mainWindow.setRight(targetView);
  }

  @FXML
  public void signOut() throws IOException {
    String confirmationMessage = "Deseja mesmo sair?";

    MainController.openModal(confirmationMessage, true);

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
