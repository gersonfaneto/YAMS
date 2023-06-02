package com.gersonfaneto.yams.controllers;

import java.io.IOException;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.models.entities.user.UserType;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
  public void signOut() throws IOException {
    Parent loginElements = FXMLLoader.load(App.class.getResource("views/login.fxml"));

    MainController.mainWindow.getChildren().removeAll();
    MainController.mainWindow.getChildren().setAll(loginElements);
  }

  @FXML
  public void openHomePane() throws IOException {
    Parent homePaneElements = FXMLLoader.load(App.class.getResource("views/home.fxml"));

    MainController.mainWindow.setRight(homePaneElements);
  }

  @FXML
  public void openClientsPane() throws IOException {
    Parent clientsPaneElements = FXMLLoader.load(App.class.getResource("views/clients.fxml"));

    MainController.mainWindow.setRight(clientsPaneElements);
  }

  @FXML
  public void openServicesPane() throws IOException {
    Parent servicesPaneElements = FXMLLoader.load(App.class.getResource("views/services.fxml"));

    MainController.mainWindow.setRight(servicesPaneElements);
  }

  @FXML
  public void openStockPane() throws IOException {
    Parent stockPaneElements = FXMLLoader.load(App.class.getResource("views/stock.fxml"));

    MainController.mainWindow.setRight(stockPaneElements);
  }

  @FXML
  public void openReportsPane() throws IOException {
    Parent reporstPaneElements = FXMLLoader.load(App.class.getResource("views/reports.fxml"));

    MainController.mainWindow.setRight(reporstPaneElements);
  }

  @FXML
  public void openEmployeesPane() throws IOException {
    Parent employeesPaneElements = FXMLLoader.load(App.class.getResource("views/employees.fxml"));

    MainController.mainWindow.setRight(employeesPaneElements);
  }
}
