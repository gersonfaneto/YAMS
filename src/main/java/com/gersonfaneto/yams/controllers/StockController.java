package com.gersonfaneto.yams.controllers;

import java.io.IOException;
import java.util.List;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

  private final ObservableList<Component> componentsLists = FXCollections.observableArrayList();
  private final FilteredList<Component> filteredComponents = new FilteredList<>(componentsLists);

  @FXML
  public void initialize() {
    amountColumn.setCellValueFactory(new PropertyValueFactory<>("amountInStock"));
    costColumn.setCellValueFactory(new PropertyValueFactory<>("componentCost"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("componentPrice"));
    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("componentDescription"));

    typeFilter.getItems().addAll(
      List.of("Todos", "RAM", "Placa Mãe", "Fonte", "Placa de Vídeo", "HD", "SSD", "Outros")
    );

    List<Component> allComponents = DAO.fromComponents().findMany();

    componentsLists.addAll(allComponents);

    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredComponents.setPredicate(user -> {
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if (user.getComponentDescription().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
          return true;
        }
        else {
          return false;
        }
      });
    });

    SortedList<Component> sortedComponents = new SortedList<>(filteredComponents);

    sortedComponents.comparatorProperty().bind(componentsTable.comparatorProperty());

    componentsTable.setItems(sortedComponents);
  }

  @FXML
  public void filterSearch() {
    componentsTable.setItems(filteredComponents.filtered(component -> {
      String typeValue = typeFilter.getValue();
      ComponentType componentType = null;

      switch (typeValue) {
        case "RAM":
          componentType = ComponentType.RAM;
          break;
        case "Fonte":
          componentType = ComponentType.PowerSupply;
          break;
        case "Placa de Vídeo":
          componentType = ComponentType.GraphicsCard;
          break;
        case "Placa Mãe":
          componentType = ComponentType.Motherboard;
          break;
        case "SSD":
          componentType = ComponentType.SSD;
          break;
        case "HD":
          componentType = ComponentType.HD;
          break;
        case "Outros":
          componentType = ComponentType.Others;
        default:
          break;
      }

      return componentType == null || component.getComponentType() == componentType;
    }));

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
