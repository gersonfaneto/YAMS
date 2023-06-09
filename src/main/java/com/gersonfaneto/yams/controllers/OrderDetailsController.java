package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.services.order.WorkOrder;
import com.gersonfaneto.yams.models.services.order.WorkOrderState;
import com.gersonfaneto.yams.models.services.service.Service;
import com.gersonfaneto.yams.models.services.service.ServiceType;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.utils.TypeParser;
import com.gersonfaneto.yams.views.components.ComponentSize;
import com.gersonfaneto.yams.views.components.ServicesListComponent;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.util.Calendar;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class OrderDetailsController {

  @FXML private FontAwesomeIconView closeButton;

  @FXML private TextField searchField;

  @FXML private ComboBox<String> typeFilter;

  @FXML private ListView<Service> listView;

  @FXML private Label visualFeedback;

  @FXML private TextField clientNameField;

  @FXML private TextField technicianNameField;

  @FXML private Button cancelOrderButton;

  private ObservableList<Service> servicesList;
  private FilteredList<Service> filteredServices;

  private WorkOrder workOrder;

  @FXML
  public void initialize() {
    servicesList = FXCollections.observableArrayList();
    filteredServices = new FilteredList<>(servicesList, x -> true);

    typeFilter.getItems().add("Todos");
    for (ServiceType serviceType : ServiceType.values()) {
      typeFilter.getItems().add(TypeParser.parseServiceType(serviceType));
    }

    listView.setCellFactory(
        listView ->
            new ListCell<Service>() {
              @Override
              protected void updateItem(Service service, boolean empty) {
                super.updateItem(service, empty);

                ComponentSize componentSize =
                    (service != null && service.getServiceType() == ServiceType.Assembly)
                        ? ComponentSize.Medium
                        : ComponentSize.Small;

                if (service == null || empty) {
                  setGraphic(null);
                } else {
                  ServicesListComponent clientComponent =
                      new ServicesListComponent(service, servicesList, componentSize, false);

                  setGraphic(clientComponent);
                }
              }
            });

    List<Service> allServices = DAO.fromService().findByWorkOrder(workOrder.getWorkOrderID());

    servicesList.addAll(allServices);

    searchField
        .textProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              filteredServices.setPredicate(
                  service -> {
                    if (newValue == null || newValue.isEmpty()) {
                      return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (service.getServiceDescription().toLowerCase().indexOf(lowerCaseFilter)
                        != -1) {
                      return true;
                    } else {
                      return false;
                    }
                  });
            });

    SortedList<Service> sortedServices = new SortedList<>(filteredServices);

    listView.setItems(sortedServices);

    clientNameField.setText(DAO.fromClients().findByID(workOrder.getClientID()).getClientName());

    technicianNameField.setText(
        (workOrder.getTechnicianID() == null)
            ? "Ordem não iniciada!"
            : DAO.fromUsers().findByID(workOrder.getTechnicianID()).getUserName());

    if (workOrder.getWorkOrderState() != WorkOrderState.Created) {
      cancelOrderButton.setVisible(false);
    }
  }

  @FXML
  public void filterSearch() {
    listView.setItems(
        filteredServices.filtered(
            service -> {
              String typeValue = typeFilter.getValue();

              ServiceType serviceType = TypeParser.parseServiceType(typeValue);

              return serviceType == null || service.getServiceType() == serviceType;
            }));
  }

  @FXML
  public void cancelOrder() throws IOException {
    String confirmationMessage = "Deseja mesmo cancelar a ordem?";

    MainController.openModal(confirmationMessage, true);

    if (MainController.isConfirmed) {
      List<Service> relatedServices = DAO.fromService().findByWorkOrder(workOrder.getWorkOrderID());

      for (Service currentService : relatedServices) {
        if (currentService.getServiceType() == ServiceType.Assembly) {
          Component usedComponent = currentService.getUsedComponent();
          Component foundComponent = DAO.fromComponents().findEquals(usedComponent);

          if (foundComponent != null) {
            foundComponent.setAmountInStock(
                foundComponent.getAmountInStock() + currentService.getAmountUsed());
            DAO.fromComponents().updateInformation(foundComponent);
          } else {
            DAO.fromComponents().createOne(usedComponent);
          }
        }
      }

      workOrder.setWorkOrderState(WorkOrderState.Canceled);
      workOrder.setClosedAt(Calendar.getInstance());

      DAO.fromWorkOrders().updateInformation(workOrder);

      closeDetails();
    }
  }

  @FXML
  public void closeDetails() throws IOException {
    Parent servicesView = FXMLLoader.load(App.class.getResource("views/services/Main.fxml"));

    MainController.mainWindow.setRight(servicesView);
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }

  public void setWorkOrder(WorkOrder workOrder) {
    this.workOrder = workOrder;
  }

  public WorkOrder getWorkOrder() {
    return workOrder;
  }
}
