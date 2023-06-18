package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;
import com.gersonfaneto.yams.utils.TypeParser;
import com.gersonfaneto.yams.views.components.ComponentSize;
import com.gersonfaneto.yams.views.components.ComponentsListComponent;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class StockController {

  @FXML private FontAwesomeIconView closeButton;

  @FXML private ListView<Component> listView;

  @FXML private TextField searchField;

  @FXML private ComboBox<String> typeFilter;

  private ObservableList<Component> componentsLists;
  private FilteredList<Component> filteredComponents;

  @FXML
  public void initialize() {
    componentsLists = FXCollections.observableArrayList();
    filteredComponents = new FilteredList<>(componentsLists);

    typeFilter.getItems().add("Todos");
    for (ComponentType componentType : ComponentType.values()) {
      typeFilter.getItems().add(TypeParser.parseComponentType(componentType));
    }

    listView.setCellFactory(
        listView ->
            new ListCell<Component>() {
              @Override
              protected void updateItem(Component component, boolean empty) {
                super.updateItem(component, empty);

                if (component == null || empty) {
                  setGraphic(null);
                } else {
                  ComponentsListComponent clientComponent =
                      new ComponentsListComponent(component, componentsLists, ComponentSize.Medium);

                  setGraphic(clientComponent);
                }
              }
            });

    List<Component> allComponents = DAO.fromComponents().findMany();

    componentsLists.addAll(allComponents);

    searchField
        .textProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              filteredComponents.setPredicate(
                  user -> {
                    if (newValue == null || newValue.isEmpty()) {
                      return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (user.getComponentDescription().toLowerCase().indexOf(lowerCaseFilter)
                        != -1) {
                      return true;
                    } else {
                      return false;
                    }
                  });
            });

    SortedList<Component> sortedComponents = new SortedList<>(filteredComponents);

    listView.setItems(sortedComponents);
  }

  @FXML
  public void filterSearch() {
    listView.setItems(
        filteredComponents.filtered(
            component -> {
              String typeValue = typeFilter.getValue();

              if (typeValue.equals("Todos")) {
                return true;
              }

              ComponentType componentType = TypeParser.parseComponentType(typeValue);

              return componentType == null || component.getComponentType() == componentType;
            }));
  }

  @FXML
  public void openPurchase() throws IOException {
    Parent purchaseComponentElements =
        FXMLLoader.load(App.class.getResource("views/stock/PurchaseComponent.fxml"));

    MainController.mainWindow.setRight(purchaseComponentElements);
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }
}
