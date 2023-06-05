package com.gersonfaneto.yams.controllers;

import java.io.IOException;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.models.entities.user.UserType;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class MenuController {
  @FXML
  private FontAwesomeIconView signOutButton;

  @FXML
  private FontAwesomeIconView employeesIcon;

  @FXML
  private FontAwesomeIconView homeIcon;

  @FXML
  private Button homeButton;

  @FXML
  private Button clientsButton;

  @FXML
  private Button servicesButton;

  @FXML
  private Button stockButton;

  @FXML
  private Button reportsButton;

  @FXML
  private Button employeesButton;

  public void initialize() {
    if (MainController.loggedUser.getUserType() == UserType.Administrator) {
      homeButton.setVisible(false);
      homeIcon.setVisible(false);
    }
    else if (MainController.loggedUser.getUserType() == UserType.Receptionist) {
      homeButton.setVisible(false);
      homeIcon.setVisible(false);
      employeesButton.setVisible(false);
      employeesIcon.setVisible(false);
    }
    else {
      employeesButton.setVisible(false);
      employeesIcon.setVisible(false);
    }
  }

  @FXML
  public void handleClicks(ActionEvent actionEvent) throws IOException {
    Parent targetView = null;

    if (actionEvent.getSource() == homeButton) {
      targetView = FXMLLoader.load(App.class.getResource("views/home.fxml"));
    }
    else if (actionEvent.getSource() == clientsButton) {
      targetView = FXMLLoader.load(App.class.getResource("views/clients.fxml"));
    }
    else if (actionEvent.getSource() == servicesButton) {
      targetView = FXMLLoader.load(App.class.getResource("views/services.fxml"));
    }
    else if (actionEvent.getSource() == stockButton) {
      targetView = FXMLLoader.load(App.class.getResource("views/stock.fxml"));
    }
    else if (actionEvent.getSource() == reportsButton) {
      targetView = FXMLLoader.load(App.class.getResource("views/reports.fxml"));
    }
    else if (actionEvent.getSource() == employeesButton) {
      targetView = FXMLLoader.load(App.class.getResource("views/employees.fxml"));
    }

    MainController.mainWindow.setRight(targetView);
  }

  @FXML
  public void signOut() throws IOException {
    Parent loginElements = FXMLLoader.load(App.class.getResource("views/login.fxml"));

    MainController.mainWindow.getChildren().removeAll();
    MainController.mainWindow.getChildren().setAll(loginElements);
  }
}
