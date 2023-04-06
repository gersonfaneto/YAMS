package com.gersonfaneto.yams;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Hello, World!");
    Button primaryButton = new Button();
    primaryButton.setText("Say \"Hello, World!\"");
    primaryButton.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        System.out.println("Hello World!");
      }
    });

    StackPane primaryPane = new StackPane();
    primaryPane.getChildren().add(primaryButton);
    primaryStage.setScene(new Scene(primaryPane, 300, 250));
    primaryStage.show();
  }
}