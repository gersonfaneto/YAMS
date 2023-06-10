package com.gersonfaneto.yams.controllers;

import java.io.IOException;
import java.util.List;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.services.ServiceType;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;
import com.gersonfaneto.yams.utils.TypeParser;
import com.gersonfaneto.yams.views.components.ComponentsListComponent;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CreateServiceController {

  @FXML
  private FontAwesomeIconView backButton;

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private ComboBox<String> componentTypeFilter;

  @FXML
  private TextField searchField;

  @FXML
  private ComboBox<String> serviceTypeFilter;

  @FXML
  private ListView<Component> listView;

  @FXML
  private TextField descriptionField;

  @FXML
  private TextField amountField;

  @FXML
  private Button confirmButton;

  @FXML
  private Button cancelButton;

  private ObservableList<Component> componentsLists;
  private FilteredList<Component> filteredComponents;

  @FXML
  public void initialize() {
    populateTable();

    componentTypeFilter.getItems().add("Todos");
    for (ComponentType componentType : ComponentType.values()) {
      componentTypeFilter.getItems().add(TypeParser.parseComponentType(componentType));
    }

    for (ServiceType serviceType : ServiceType.values()) {
      serviceTypeFilter.getItems().add(TypeParser.parseServiceType(serviceType));
    }

    amountField.setVisible(false);
  }

  @FXML
  public void filterSearch() {
    listView.setItems(filteredComponents.filtered(component -> {
      String typeValue = componentTypeFilter.getValue();

      if (typeValue.equals("Todos")) {
        return true;
      }

      ComponentType componentType = TypeParser.parseComponentType(typeValue);

      return componentType == null || component.getComponentType() == componentType;
    }));
  }

  @FXML
  public void cancelRegister() throws IOException {
    Parent createOrderView = FXMLLoader.load(App.class.getResource("views/create_order.fxml"));

    MainController.mainWindow.setRight(createOrderView);
  }

  @FXML
  public void confirmRegister() {
  }

  @FXML
  public void checkType() {
    if (serviceTypeFilter.getValue().equals("Montagem")) {
      amountField.setVisible(true);
      return;
    }
    amountField.setVisible(false);
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }


  public void populateTable() {
    componentsLists = FXCollections.observableArrayList();
    filteredComponents = new FilteredList<>(componentsLists);

    listView.setCellFactory(listView -> new ListCell<Component>() {
      @Override
      protected void updateItem(Component component, boolean empty) {
        super.updateItem(component, empty);

        if (component == null || empty) {
          setGraphic(null);
        } else {
          ComponentsListComponent clientComponent = new ComponentsListComponent(component,
              componentsLists);

          setGraphic(clientComponent);
        }
      }
    });

    List<Component> allComponents = DAO.fromComponents().findMany();

    componentsLists.addAll(allComponents);

    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredComponents.setPredicate(user -> {
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if (user.getComponentDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
          return true;
        } else {
          return false;
        }
      });
    });

    SortedList<Component> sortedComponents = new SortedList<>(filteredComponents);

    listView.setItems(sortedComponents);
  }
}
