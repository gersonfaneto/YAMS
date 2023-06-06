package com.gersonfaneto.yams.controllers;

import java.io.IOException;
import java.util.List;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;
import com.gersonfaneto.yams.utils.TypeParser;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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
      priceField.setDisable(false);
      priceField.clear();
    }
    else {
      typeField.setVisible(false);
      injectPrice();
    }
  }

  @FXML
  public void injectPrice() {
    String typeName = typeSelector.getValue();

    if (typeName.equals("Outro")) {
      return;
    }

    priceField.setDisable(true);

    double typeValue = TypeParser.parseComponentType(typeName).getTypeValue();
    priceField.setText(Double.toString(typeValue));
  }

  @FXML
  public void cancelPurchase() throws IOException {
    Parent stockView = FXMLLoader.load(App.class.getResource("views/stock.fxml"));

    MainController.mainWindow.setRight(stockView);
  }

  @FXML
  public void confirmPurchase() {
    String componentDescription = descriptionField.getText();
    String typeName = getType();
    int amountBought = getAmmount();
    double componentCost = getCost();
    double componentPrice = getCost();

    if (componentDescription.isEmpty()) {
      visualFeedback.setText("Descrição inválida!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }
    if (typeSelector.getValue() == null || typeField.isVisible() && typeName.isEmpty()) {
      visualFeedback.setText("Tipo inválido!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }
    if (amountSelector.getValue() == null || amountField.isVisible() && amountBought == -1) {
      visualFeedback.setText("Quantida inválida!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }
    if (typeField.isVisible() && componentPrice == -1) {
      visualFeedback.setText("Preço inválido!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }
    if (componentCost == -1) {
      visualFeedback.setText("Custo inválido!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    descriptionField.clear();
    typeField.clear();
    amountField.clear();
    costField.clear();
    priceField.clear();
    // TODO: Find a way to clear the ComboBoxes after. The following code doesn't work.
    // typeSelector.getSelectionModel().clearSelection();
    // typeSelector.getSelectionModel().clearSelection();

    ComponentType typeValue = TypeParser.parseComponentType(typeName);

    Component boughtComponent = new Component(
      typeValue,
      componentDescription,
      amountBought,
      componentCost,
      componentPrice
    );

    Component foundComponent = DAO.fromComponents().findEquals(boughtComponent);

    if (foundComponent == null) {
      DAO.fromComponents().createOne(boughtComponent);
    }
    else {
      foundComponent.setAmountInStock(foundComponent.getAmountInStock() + amountBought);
      DAO.fromComponents().updateInformation(foundComponent);
    }

    visualFeedback.setText("Compra registrada com sucesso!");
    visualFeedback.setTextFill(Color.GREEN);
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }

  public double getCost() {
    try {
      double costValue = Double.parseDouble(costField.getText());
      
      return (costValue > 0) ? costValue : -1;
    }
    catch (NumberFormatException nfe) {
      return -1;
    }
  }

  public double getPrice() {
    try {
      double priceValue = Double.parseDouble(priceField.getText());

      return (priceValue > 0) ? priceValue : -1;
    }
    catch (NumberFormatException nfe) {
      return -1;
    }
  }

  public String getType() {
    if (typeField.isVisible()) {
      return typeField.getText();
    }
    return typeSelector.getValue();
  }

  public int getAmmount() {
    if (amountField.isVisible()) {
      try {
        int amountValue = Integer.parseInt(amountField.getText());

        return (amountValue > 0) ? amountValue : -1;
      }
      catch (NumberFormatException nfe) {
        return -1;
      }
    }
    try {
      int amountValue = Integer.parseInt(amountSelector.getValue());

      return (amountValue > 0) ? amountValue : -1;
    }
    catch (NumberFormatException nfe) {
      return -1;
    }
  }
}
