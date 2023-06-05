package com.gersonfaneto.yams.controllers;

import java.io.IOException;

import com.gersonfaneto.yams.App;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class PurchaseComponentController {
  @FXML
  private FontAwesomeIconView backButton;

  @FXML
  private Button cancelButton;

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private Button confirmButton;

  @FXML
  public void initialize() {

  }

  @FXML
  void cancelRegister() throws IOException {
    Parent stockElements = FXMLLoader.load(App.class.getResource("views/stock.fxml"));

    MainController.mainWindow.setRight(stockElements);
  }

  @FXML
  void confirmRegister() {

  }

  @FXML
  void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }
}

