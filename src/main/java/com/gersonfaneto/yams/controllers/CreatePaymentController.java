package com.gersonfaneto.yams.controllers;

import java.io.IOException;

import com.gersonfaneto.yams.App;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreatePaymentController {
  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private TextField responsibleNameField;

  @FXML
  private TextField clientNameField;

  @FXML
  private ComboBox<String> paymentMethodSelector;

  @FXML
  private TextField paymentValueField;

  @FXML
  private Button cancelButton;

  @FXML
  private Button confirmButton;


  @FXML
  public void handleClicks(ActionEvent event) {

  }

  @FXML
  public void closeWindow() throws IOException {
    Parent invoicesView = FXMLLoader.load(App.class.getResource("views/invoices.fxml"));

    MainController.mainWindow.setRight(invoicesView);

    MainController.modalStage.close();
  }

  public void injectFields(String responsibleName, String clientName) {
    responsibleNameField.setText(responsibleName);
    clientNameField.setText(clientName);
  }
}

