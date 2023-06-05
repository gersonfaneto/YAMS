package com.gersonfaneto.yams.controllers;

import java.io.IOException;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.models.stock.Component;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StockController {
  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private TableView<Component> componentsTable;

  @FXML
  private TableColumn<Component, Integer> amountColumn;

  @FXML
  private TableColumn<Component, Double> costColumn;

  @FXML
  private TableColumn<Component, Double> priceColumn;

  @FXML
  private TableColumn<Component, String> descriptionColumn;

  @FXML
  private TextField searchField;

  @FXML
  private ComboBox<String> typeFilter;

  @FXML
  public void initialize() {
    typeFilter.getItems().add("RAM");
    typeFilter.getItems().add("Placa Mãe");
    typeFilter.getItems().add("Fonte");
    typeFilter.getItems().add("Placa de Vídeo");
    typeFilter.getItems().add("HD/SSD");
    typeFilter.getItems().add("Outros");
  }

  @FXML
  public void filterSearch() {

  }

  @FXML
  public void openPurchase() throws IOException {
    Parent purchaseComponentElements = FXMLLoader.load(App.class.getResource("views/purchase_component.fxml"));

    MainController.mainWindow.setRight(purchaseComponentElements);
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }
}
