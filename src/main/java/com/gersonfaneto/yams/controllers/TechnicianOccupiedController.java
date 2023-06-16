package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.utils.Time;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

