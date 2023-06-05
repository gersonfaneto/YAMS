package com.gersonfaneto.yams.controllers;

import java.io.IOException;
import java.util.List;

import com.gersonfaneto.yams.App;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PurchaseComponentController {

  @FXML
  private FontAwesomeIconView backButton;

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private Label visualFeedback;

  @FXML
  private TextField descriptionField;

  @FXML
  private ComboBox<String> typeSelector;

  @FXML
  private TextField typeField;

  @FXML
  private ComboBox<String> amountSelector;

  @FXML
  private TextField amountField;

  @FXML
  private TextField costField;

  @FXML
  private TextField priceField;

  @FXML
  private Button cancelButton;

  @FXML
  private Button confirmButton;


  @FXML
  public void initialize() {
    amountSelector.getItems().addAll(
      List.of("5", "10", "15", "20", "Outro")
    );
    amountField.setVisible(false);

    typeSelector.getItems().addAll(
      List.of("RAM", "Placa Mãe", "Fonte", "Placa de Vídeo", "HD", "SSD", "Outro")
    );
    typeField.setVisible(false);
  }

  @FXML
  public void checkAmount() {
    if (amountSelector.getValue().equals("Outro")) {
      amountField.setVisible(true);
    }
    else {
      amountField.setVisible(false);
    }
  }

  @FXML
  public void checkType() {
    if (typeSelector.getValue().equals("Outro")) {
      typeField.setVisible(true);
    }
    else {
      typeField.setVisible(false);
    }
  }

  @FXML
  void cancelRegister() throws IOException {
    Parent stockElements = FXMLLoader.load(App.class.getResource("views/stock.fxml"));

    MainController.mainWindow.setRight(stockElements);
  }

  @FXML
  void confirmPurchase() {

  }

  @FXML
  void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }
}
