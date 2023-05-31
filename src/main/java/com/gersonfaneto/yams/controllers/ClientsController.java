package com.gersonfaneto.yams.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;

public class ClientsController {
  @FXML private FontAwesomeIconView closeButton;

  @FXML
  public void initialize() {}

  @FXML
  public void closeWindow() {
    System.exit(0);
  }
}
