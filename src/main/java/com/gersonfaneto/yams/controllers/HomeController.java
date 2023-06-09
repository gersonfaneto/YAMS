package com.gersonfaneto.yams.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;

public class HomeController {

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  public void initialize() {
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }
}
