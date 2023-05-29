package com.gersonfaneto.yams;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));

    Scene loginScene = new Scene(root);

    primaryStage.setResizable(false);
    primaryStage.initStyle(StageStyle.UNDECORATED);

    primaryStage.setScene(loginScene);
    primaryStage.show();
  }
}
