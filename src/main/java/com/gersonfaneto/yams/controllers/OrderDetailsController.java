package com.gersonfaneto.yams.controllers;

import java.io.IOException;
import java.util.List;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.orders.work.WorkOrderState;
import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.models.services.ServiceType;
import com.gersonfaneto.yams.utils.TypeParser;
import com.gersonfaneto.yams.views.components.ComponentSize;
import com.gersonfaneto.yams.views.components.ServicesListComponent;
import com.gersonfaneto.yams.views.windows.ActionConfirmationDialog;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OrderDetailsController {

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private TextField searchField;

  @FXML
  private ComboBox<String> typeFilter;

  @FXML
  private ListView<Service> listView;

  @FXML
  private Label visualFeedback;

  @FXML
  private TextField clientNameField;

  @FXML
  private TextField technicianNameField;

  @FXML
  private Button cancelOrderButton;

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

    listView.setCellFactory(listView -> new ListCell<Service>() {
      @Override
      protected void updateItem(Service service, boolean empty) {
        super.updateItem(service, empty);

        ComponentSize componentSize = (service != null && service.getServiceType() == ServiceType.Assembly)
          ? ComponentSize.Medium
          : ComponentSize.Small;

        if (service == null || empty) {
          setGraphic(null);
        } else {
          ServicesListComponent clientComponent = new ServicesListComponent(
              service,
              servicesList,
              componentSize,
              false
          );

          setGraphic(clientComponent);
        }
      }
    });

    List<Service> allServices = DAO.fromService().findByWorkOrder(workOrder.getWorkOrderID());

    servicesList.addAll(allServices);

    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredServices.setPredicate(service -> {
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if (service.getServiceDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
          return true;
        } else {
          return false;
        }
      });
    });

    SortedList<Service> sortedServices = new SortedList<>(filteredServices);

    listView.setItems(sortedServices);

    clientNameField.setText(
        DAO.fromClients().findByID(workOrder.getClientID()).getClientName()
    );

    technicianNameField.setText(
        (workOrder.getTechnicianID() == null)
          ? "Ordem não iniciada!"
          : DAO.fromUsers().findByID(workOrder.getTechnicianID()).getUserName()
    );

    if (workOrder.getWorkOrderState() != WorkOrderState.Created) {
      cancelOrderButton.setVisible(false);
    }
  }

  @FXML
  public void filterSearch() {
    listView.setItems(filteredServices.filtered(service -> {
      String typeValue = typeFilter.getValue();

      ServiceType serviceType = TypeParser.parseServiceType(typeValue);

      return serviceType == null || service.getServiceType() == serviceType;
    }));
  }

  @FXML
  public void cancelOrder() throws IOException {
    String confirmationMessage = "Deseja mesmo cancelar a ordem?";

    ActionConfirmationDialog confirmDialog = new ActionConfirmationDialog(confirmationMessage);

    Stage modalStage = new Stage();

    MainController.modalStage = modalStage;

    modalStage.setScene(new Scene(confirmDialog));
    modalStage.initStyle(StageStyle.UNDECORATED);
    modalStage.initModality(Modality.APPLICATION_MODAL);
    modalStage.initOwner(MainController.primaryStage);
    modalStage.showAndWait();

    if (MainController.isConfirmed) {
      workOrder.setWorkOrderState(WorkOrderState.Canceled);

      DAO.fromWorkOrders().updateInformation(workOrder);

      closeDetails();
    }
  }

  @FXML
  public void closeDetails() throws IOException {
    Parent servicesView = FXMLLoader.load(App.class.getResource("views/services.fxml"));

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
