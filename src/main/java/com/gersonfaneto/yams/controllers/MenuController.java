package com.gersonfaneto.yams.controllers;

import java.io.IOException;

import com.gersonfaneto.yams.App;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MenuController {
  @FXML
  private FontAwesomeIconView backButton;

  public static BorderPane mainWindow = null;

  public void initialize() throws IOException {
  }

  @FXML
  public void resumeApp() throws IOException {
    Parent loginElements = FXMLLoader.load(App.class.getResource("views/login.fxml"));

    mainWindow.getChildren().removeAll();
    mainWindow.getChildren().setAll(loginElements);
  }
}
