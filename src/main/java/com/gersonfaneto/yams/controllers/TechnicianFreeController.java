package com.gersonfaneto.yams.controllers;

import java.io.IOException;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.entities.technician.TechnicianStatus;
import com.gersonfaneto.yams.models.services.order.WorkOrder;
import com.gersonfaneto.yams.models.services.order.WorkOrderState;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class TechnicianFreeController {
  @FXML
  public FontAwesomeIconView closeButton;

  @FXML
  public Button openOrderButton;

  private Technician loggedTechnician;

  @FXML
  public void initialize() {
    this.loggedTechnician = (Technician) MainController.loggedUser;
  }

  @FXML
  public void openOrder() throws IOException {
    WorkOrder foundWorkOrder = DAO.fromWorkOrders().findMany()
      .stream()
      .filter(workOrder -> {
        return workOrder.getWorkOrderState() == WorkOrderState.Created;
      })
      .min((firstWorkOrder, secondWorkOrder) -> {
        return firstWorkOrder.getCreatedAt()
          .compareTo(secondWorkOrder.getCreatedAt());
      })
      .orElse(null);

    if (foundWorkOrder == null) {
      String confirmationMessage = "Nenhuma ordem em espera!";

      MainController.openModal(confirmationMessage, false);

      return;
    } 

    foundWorkOrder.setTechnicianID(loggedTechnician.getUserID());

    loggedTechnician.setStatus(TechnicianStatus.Occupied);
    foundWorkOrder.setWorkOrderState(WorkOrderState.Open);

    DAO.fromUsers().updateInformation(loggedTechnician);
    DAO.fromWorkOrders().updateInformation(foundWorkOrder);

    MainController.openOrder = foundWorkOrder;

    Parent homeView = FXMLLoader.load(App.class.getResource("views/home/Main.fxml"));

    MainController.mainWindow.setRight(homeView);
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }
}
