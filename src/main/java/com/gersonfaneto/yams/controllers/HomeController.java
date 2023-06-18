package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class HomeController {

  @FXML private BorderPane homeWindow;

  @FXML private FontAwesomeIconView closeButton;

  @FXML private Button openOrderButton;

  @FXML private Label nameLabel;

  @FXML
  public void initialize() throws IOException {
    if (MainController.homeWindow == null) {
      MainController.homeWindow = homeWindow;
    }

    String sideViewPath =
        "views/home/Technician%s.fxml"
            .formatted(((Technician) MainController.loggedUser).getStatus().getStatusName());

    Parent sideView = FXMLLoader.load(App.class.getResource(sideViewPath));

    homeWindow.setRight(sideView);

    nameLabel.setText(MainController.loggedUser.getUserName());
  }

  @FXML
  public void openOrder() {
    System.out.println("HomeController.openOrder()");
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }
}
