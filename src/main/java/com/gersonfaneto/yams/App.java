package com.gersonfaneto.yams;

import com.gersonfaneto.yams.controllers.MainController;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class App extends Application {

  private double xCoord, yCoord;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent rootView = FXMLLoader.load(getClass().getResource("views/login.fxml"));

    rootView.setOnMousePressed(event -> {
      xCoord = event.getSceneX();
      yCoord = event.getSceneY();
    });

    rootView.setOnMouseDragged(event -> {
      primaryStage.setX(event.getScreenX() - xCoord);
      primaryStage.setY(event.getScreenY() - yCoord);
    });

    Scene loginScene = new Scene(rootView);

    Image appIcon = new Image(getClass().getResourceAsStream("assets/Logo.png"));

    primaryStage.setResizable(false);
    primaryStage.initStyle(StageStyle.UNDECORATED);

    primaryStage.getIcons().add(appIcon);

    primaryStage.setScene(loginScene);
    primaryStage.show();

    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent windowEvent) {
        MainController.saveData();
      }
    });

    MainController.primaryStage = primaryStage;
  }
}