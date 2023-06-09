package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.client.Client;
import com.gersonfaneto.yams.models.services.order.WorkOrder;
import com.gersonfaneto.yams.models.services.service.Service;
import com.gersonfaneto.yams.models.services.service.ServiceType;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.views.components.ClientsListComponent;
import com.gersonfaneto.yams.views.components.ComponentSize;
import com.gersonfaneto.yams.views.components.ServicesListComponent;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class CreateOrderController {

  @FXML private FontAwesomeIconView backButton;

  @FXML private FontAwesomeIconView closeButton;

  @FXML private Label visualFeedback;

  @FXML private Button addService;

  @FXML private TextField searchField;

  @FXML private ListView<Client> clientsListView;

  @FXML private ListView<Service> servicesListView;

  @FXML private Button confirmButton;

  @FXML private Button cancelButton;

  private ObservableList<Client> clientsList;
  private FilteredList<Client> filteredClients;

  private ObservableList<Service> servicesList;

  @FXML
  public void initialize() {
    clientsList = FXCollections.observableArrayList();
    filteredClients = new FilteredList<>(clientsList, x -> true);

    servicesList = FXCollections.observableArrayList();

    clientsListView.setCellFactory(
        listView ->
            new ListCell<Client>() {
              @Override
              protected void updateItem(Client client, boolean empty) {
                super.updateItem(client, empty);

                if (client == null || empty) {
                  setGraphic(null);
                } else {
                  ClientsListComponent clientComponent =
                      new ClientsListComponent(client, clientsList, ComponentSize.Small);

                  setGraphic(clientComponent);
                }
              }
            });

    servicesListView.setCellFactory(
        listView ->
            new ListCell<Service>() {
              @Override
              protected void updateItem(Service service, boolean empty) {
                super.updateItem(service, empty);

                if (service == null || empty) {
                  setGraphic(null);
                } else {
                  ServicesListComponent clientComponent =
                      new ServicesListComponent(service, servicesList, ComponentSize.Small, false);

                  setGraphic(clientComponent);
                }
              }
            });

    List<Client> allClients = DAO.fromClients().findMany();
    List<Service> relatedServices = DAO.fromService().findByWorkOrder("TEMP");

    clientsList.addAll(allClients);
    servicesList.addAll(relatedServices);

    searchField
        .textProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              filteredClients.setPredicate(
                  user -> {
                    if (newValue == null || newValue.isEmpty()) {
                      return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (user.getClientName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                      return true;
                    } else {
                      return false;
                    }
                  });
            });

    SortedList<Client> sortedClients = new SortedList<>(filteredClients);

    clientsListView.setItems(sortedClients);
    servicesListView.setItems(servicesList);
  }

  @FXML
  public void cancelRegister() throws IOException {
    List<Service> allServices = DAO.fromService().findByWorkOrder("TEMP");

    for (Service service : allServices) {
      if (service.getServiceType() == ServiceType.Assembly) {
        Component usedComponent = service.getUsedComponent();
        Component storedComponent = DAO.fromComponents().findEquals(usedComponent);

        storedComponent.setAmountInStock(
            storedComponent.getAmountInStock() + service.getAmountUsed());

        DAO.fromComponents().updateInformation(storedComponent);
      }
      DAO.fromService().deleteByID(service.getServiceID());
    }

    DAO.fromWorkOrders().deleteByID("TEMP");

    Parent servicesView = FXMLLoader.load(App.class.getResource("views/services/Main.fxml"));

    MainController.mainWindow.setRight(servicesView);
  }

  @FXML
  public void confirmRegister() throws IOException {
    Client targetClient = clientsListView.getSelectionModel().getSelectedItem();
    List<Service> selectedServices = servicesListView.getItems();

    if (targetClient == null) {
      visualFeedback.setText("Selecione o cliente!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    if (selectedServices.size() == 0) {
      visualFeedback.setText("Ordem não pode ser aberta sem ao menos um serviço!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    WorkOrder workOrder = DAO.fromWorkOrders().createOne(new WorkOrder(targetClient.getClientID()));

    for (Service service : selectedServices) {
      service.setWorkOrderID(workOrder.getWorkOrderID());
      DAO.fromService().updateInformation(service);
    }

    DAO.fromWorkOrders().deleteByID("TEMP");

    Parent servicesView = FXMLLoader.load(App.class.getResource("views/services/Main.fxml"));

    MainController.mainWindow.setRight(servicesView);
  }

  @FXML
  public void openServicesRegistration() throws IOException {
    Parent createServicesView =
        FXMLLoader.load(App.class.getResource("views/services/CreateService.fxml"));

    MainController.mainWindow.setRight(createServicesView);
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }
}
