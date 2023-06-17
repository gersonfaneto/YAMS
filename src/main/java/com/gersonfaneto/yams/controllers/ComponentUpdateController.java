package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ComponentUpdateController {

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private Label visualFeedback;

  @FXML
  private TextField descriptionField;

  @FXML
  private TextField amountField;

  @FXML
  private TextField priceField;

  @FXML
  private Button cancelButton;

  @FXML
  private Button confirmButton;

  private String componentID;

  @FXML
  public void cancelUpdate() throws IOException {
    Parent stockView = FXMLLoader.load(App.class.getResource("views/stock/Main.fxml"));

    MainController.mainWindow.setRight(stockView);

    MainController.modalStage.close();
  }

  @FXML
  public void confirmUpdate() {
    String componentDescription = descriptionField.getText();
    int amountInStock = getAmount();
    double componentPrice = getPrice();

    if (componentDescription.isEmpty()) {
      visualFeedback.setText("Descrição inválida!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    if (amountInStock == -1) {
      visualFeedback.setText("Quantida inválida!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    if (componentPrice == -1) {
      visualFeedback.setText("Preço inválido!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    descriptionField.clear();
    amountField.clear();
    priceField.clear();

    Component foundComponent = DAO.fromComponents().findByID(componentID);

    foundComponent.setComponentDescription(componentDescription);
    foundComponent.setAmountInStock(amountInStock);
    foundComponent.setComponentPrice(componentPrice);

    DAO.fromComponents().updateInformation(foundComponent);

    visualFeedback.setText("Estoque atualizado com sucesso!");
    visualFeedback.setTextFill(Color.GREEN);
  }

  public void injectFields(
      String componentID,
      String componentDescription,
      double componentPrice,
      int amountInStock
  ) {
    this.componentID = componentID;
    descriptionField.setText(componentDescription);
    priceField.setText(formatMoney(componentPrice));

    if (DAO.fromComponents().findByID(componentID).getComponentType() != ComponentType.Others) {
      priceField.setDisable(true);
    }

    amountField.setText(Integer.toString(amountInStock));
  }

  public String formatMoney(double moneyInput) {
    return String.format("%.2f", moneyInput).replace(".", ",");
  }


  public int getAmount() {
    try {
      int amountValue = Integer.parseInt(
          amountField.getText()
      );

      return (amountValue > 0) ? amountValue : -1;
    } catch (NumberFormatException nfe) {
      return -1;
    }
  }

  public double getPrice() {
    try {
      double priceValue = Double.parseDouble(
          priceField.getText().replaceFirst(",", ".")
      );

      return (priceValue > 0) ? priceValue : -1;
    } catch (NumberFormatException nfe) {
      return -1;
    }
  }
}

