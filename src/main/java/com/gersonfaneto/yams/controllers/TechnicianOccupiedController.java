package com.gersonfaneto.yams.controllers;

import java.util.List;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.utils.Time;
import com.gersonfaneto.yams.views.components.ComponentSize;
import com.gersonfaneto.yams.views.components.ServicesListComponent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TechnicianOccupiedController {
  @FXML
  private TextField clientNameField;

  @FXML
  private TextField creationDateField;

  @FXML
  private TextField priceField;

  @FXML
  private ListView<Service> listView;

  @FXML
  private Button cancelOrderButton;

  @FXML
  private Button finishOrderButton;

  private ObservableList<Service> servicesList;

  private Technician loggedTechnician;
  private WorkOrder openOrder;

  @FXML
  public void initialize() {
    this.loggedTechnician = (Technician) MainController.loggedUser;
    this.openOrder = MainController.openOrder;

    clientNameField.setText(
        DAO.fromClients().findByID(openOrder.getClientID()).getClientName()
    );

    creationDateField.setText(
        Time.extractDateFromCalendar(
          openOrder.getCreatedAt()
        )
    );

    priceField.setText(
        formatMoney(
          DAO.fromService()
            .findByWorkOrder(openOrder.getWorkOrderID())
            .stream()
            .map(Service::getServicePrice)
            .reduce(0.0, Double::sum)
        )
    );

    servicesList = FXCollections.observableArrayList();

    listView.setCellFactory(listView -> new ListCell<Service>() {
      @Override
      protected void updateItem(Service service, boolean empty) {
        super.updateItem(service, empty);

        if (service == null || empty) {
          setGraphic(null);
        } else {
          ServicesListComponent clientComponent = new ServicesListComponent(
              service,
              servicesList,
              ComponentSize.Small,
              true
          );

          setGraphic(clientComponent);
        }
      }
    });

    List<Service> relatedServices = DAO.fromService()
      .findByWorkOrder(openOrder.getWorkOrderID());

    servicesList.addAll(relatedServices);

    listView.setItems(servicesList);
  }

  @FXML
  public void handleClicks() {

  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }

  private String formatMoney(double moneyInput) {
    return String.format("%.2f", moneyInput).replace(".", ",");
  }
}

