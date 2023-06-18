package com.gersonfaneto.yams.views.windows;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.controllers.MainController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class ActionConfirmationDialog extends AnchorPane {

  private Button confirmButton;
  private Button cancelButton;

  public ActionConfirmationDialog(String confirmationMessage) {
    super.getStylesheets().clear();
    super.getStylesheets().add(App.class.getResource("stylesheets/global.css").toExternalForm());
    super.getStyleClass().add("parent");

    super.setMinSize(500, 250);
    super.setPrefSize(500, 250);
    super.setMaxSize(500, 250);

    TextArea messageField = new TextArea();

    messageField.setLayoutX(100);
    messageField.setLayoutY(50);
    messageField.setPrefSize(300, 80);
    messageField.setMinSize(300, 80);
    messageField.setMaxSize(300, 80);

    messageField.setText(confirmationMessage);
    messageField.setWrapText(true);
    messageField.setEditable(false);
    messageField.getStyleClass().add("text-area");

    FontAwesomeIconView closeIcon = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);

    closeIcon.setLayoutX(470);
    closeIcon.setLayoutY(30);
    closeIcon.setSize("20");
    closeIcon.getStyleClass().add("close-button");

    closeIcon.setOnMouseClicked(
        event -> {
          MainController.isConfirmed = false;

          MainController.modalStage.close();
        });

    confirmButton = new Button("Confirmar");
    cancelButton = new Button("Cancelar");

    confirmButton.setLayoutX(100);
    confirmButton.setLayoutY(170);
    confirmButton.setPrefSize(100, 30);
    confirmButton.getStyleClass().add("confirm-button");

    confirmButton.setOnMouseClicked(
        event -> {
          MainController.isConfirmed = true;

          MainController.modalStage.close();
        });

    cancelButton.setLayoutX(300);
    cancelButton.setLayoutY(170);
    cancelButton.setPrefSize(100, 30);
    cancelButton.getStyleClass().add("cancel-button");

    cancelButton.setOnMouseClicked(
        event -> {
          MainController.isConfirmed = false;

          MainController.modalStage.close();
        });

    super.getChildren().add(closeIcon);
    super.getChildren().add(messageField);
    super.getChildren().addAll(confirmButton, cancelButton);
  }
}
