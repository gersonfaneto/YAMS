package com.gersonfaneto.yams.controllers;

import java.util.List;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.client.Client;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.models.services.ServiceType;
import com.gersonfaneto.yams.views.components.OrdersListComponent;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ServicesController {

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private ListView<WorkOrder> listView;

  @FXML
  private TextField searchField;

  @FXML
  private Button registerButton;

  private ObservableList<WorkOrder> workOrdersList;
  private FilteredList<WorkOrder> filteredWorkOrders;

  @FXML
  public void initialize() {
    Client randomClient = DAO.fromClients().createOne(new Client("John Smith", "Constantinople", "9999999999"));

    WorkOrder newWorkOrder = DAO.fromWorkOrders().createOne(new WorkOrder(randomClient.getClientID()));

    Service randomService = new Service(ServiceType.Cleaning, "Dusty!", List.of());
    randomService.setWorkOrderID(newWorkOrder.getWorkOrderID());

    for (int i = 0; i < 5; i++) {
      DAO.fromService().createOne(randomService);
    }

    workOrdersList = FXCollections.observableArrayList();
    filteredWorkOrders = new FilteredList<>(workOrdersList);

    /* typeFilter.getItems().add("Todos");
    for (ComponentType componentType : ComponentType.values()) {
      typeFilter.getItems().add(TypeParser.parseComponentType(componentType));
    } */

    listView.setCellFactory(listView -> new ListCell<WorkOrder>() {
      @Override
      protected void updateItem(WorkOrder workOrder, boolean empty) {
        super.updateItem(workOrder, empty);

        if (workOrder == null || empty) {
          setGraphic(null);
        } else {
          OrdersListComponent orderComponent = new OrdersListComponent(
              workOrder,
              workOrdersList
          );
          setGraphic(orderComponent);
        }
      }
    });

    List<WorkOrder> allComponents = DAO.fromWorkOrders().findMany();

    workOrdersList.addAll(allComponents);

    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredWorkOrders.setPredicate(workOrder -> {
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if (DAO.fromClients().findByID(workOrder.getClientID()).getClientName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
          return true;
        }
        else {
          return false;
        }
      });
    });

    SortedList<WorkOrder> sortedWorkOrders = new SortedList<>(filteredWorkOrders);

    listView.setItems(sortedWorkOrders);
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }

  @FXML
  public void registerOrder() {

  }
}

