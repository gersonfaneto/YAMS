package com.gersonfaneto.yams.controllers;

import java.io.IOException;

import com.gersonfaneto.yams.App;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MenuController {
  @FXML
  private FontAwesomeIconView backButton;

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

  public static BorderPane mainWindow = null;

  public void initialize() {}

  @FXML
  public void resumeApp() throws IOException {
    Parent loginElements = FXMLLoader.load(App.class.getResource("views/login.fxml"));

    mainWindow.getChildren().removeAll();
    mainWindow.getChildren().setAll(loginElements);
  }

  @FXML
  public void openHomePane() throws IOException {
    Parent homePaneElements = FXMLLoader.load(App.class.getResource("views/home.fxml"));

    mainWindow.setRight(homePaneElements);
  }

  @FXML
  public void openClientsPane() throws IOException {
    Parent clientsPaneElements = FXMLLoader.load(App.class.getResource("views/clients.fxml"));

    mainWindow.setRight(clientsPaneElements);
  }

  @FXML
  public void openServicesPane() throws IOException {
    Parent servicesPaneElements = FXMLLoader.load(App.class.getResource("views/services.fxml"));

    mainWindow.setRight(servicesPaneElements);
  }

  @FXML
  public void openStockPane() throws IOException {
    Parent stockPaneElements = FXMLLoader.load(App.class.getResource("views/stock.fxml"));

    mainWindow.setRight(stockPaneElements);
  }

  @FXML
  public void openReportsPane() throws IOException {
    Parent reporstPaneElements = FXMLLoader.load(App.class.getResource("views/reports.fxml"));

    mainWindow.setRight(reporstPaneElements);
  }
}
