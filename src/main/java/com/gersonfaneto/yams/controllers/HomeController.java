package com.gersonfaneto.yams.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;

public class HomeController {
  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  public void initialize() {
    System.out.println("Welcome, Home!");
  }

  @FXML
  public void closeWindow() {
    System.exit(0);
  }
}
