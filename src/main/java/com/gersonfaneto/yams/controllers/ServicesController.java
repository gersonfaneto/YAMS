package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.services.order.WorkOrder;
import com.gersonfaneto.yams.models.services.order.WorkOrderState;
import com.gersonfaneto.yams.utils.TypeParser;
import com.gersonfaneto.yams.views.components.OrdersListComponent;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ServicesController {

  @FXML private FontAwesomeIconView closeButton;

  @FXML private ListView<WorkOrder> listView;

  @FXML private TextField searchField;

  @FXML private Button registerButton;

  @FXML private ComboBox<String> statusFilter;

  private ObservableList<WorkOrder> workOrdersList;
  private FilteredList<WorkOrder> filteredWorkOrders;

  @FXML
  public void initialize() {
    workOrdersList = FXCollections.observableArrayList();
    filteredWorkOrders = new FilteredList<>(workOrdersList);

    statusFilter.getItems().add("Todos");
    for (WorkOrderState componentType : WorkOrderState.values()) {
      statusFilter.getItems().add(TypeParser.parseWorkOrderStateType(componentType));
    }

    listView.setCellFactory(
        listView ->
            new ListCell<WorkOrder>() {
              @Override
              protected void updateItem(WorkOrder workOrder, boolean empty) {
                super.updateItem(workOrder, empty);

                if (workOrder == null || empty) {
                  setGraphic(null);
                } else {
                  OrdersListComponent orderComponent =
                      new OrdersListComponent(workOrder, workOrdersList);
                  setGraphic(orderComponent);
                }
              }
            });

    List<WorkOrder> allComponents = DAO.fromWorkOrders().findMany();

    workOrdersList.addAll(allComponents);

    searchField
        .textProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              filteredWorkOrders.setPredicate(
                  workOrder -> {
                    if (newValue == null || newValue.isEmpty()) {
                      return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (DAO.fromClients()
                            .findByID(workOrder.getClientID())
                            .getClientName()
                            .toLowerCase()
                            .indexOf(lowerCaseFilter)
                        != -1) {
                      return true;
                    } else {
                      return false;
                    }
                  });
            });

    SortedList<WorkOrder> sortedWorkOrders = new SortedList<>(filteredWorkOrders);

    listView.setItems(sortedWorkOrders);
  }

  @FXML
  public void filterSearch() {
    listView.setItems(
        filteredWorkOrders.filtered(
            workOrder -> {
              String statusValue = statusFilter.getValue();

              WorkOrderState statusType = TypeParser.parseWorkOrderStateType(statusValue);

              return statusType == null || workOrder.getWorkOrderState() == statusType;
            }));
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }

  @FXML
  public void registerOrder() throws IOException {
    Parent clientRegisterView =
        FXMLLoader.load(App.class.getResource("views/services/CreateOrder.fxml"));

    MainController.mainWindow.setRight(clientRegisterView);
  }
}
