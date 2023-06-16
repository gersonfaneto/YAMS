package com.gersonfaneto.yams.controllers;

import java.io.IOException;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.entities.technician.TechnicianStatus;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.orders.work.WorkOrderState;
import com.gersonfaneto.yams.views.windows.ActionConfirmationDialog;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TechnicianController {
  @FXML
  public Button openOrderButton;

  @FXML
  public FontAwesomeIconView closeButton;

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

      ActionConfirmationDialog confirmDialog = new ActionConfirmationDialog(confirmationMessage);

      Stage modalStage = new Stage();

      MainController.modalStage = modalStage;

      modalStage.setScene(new Scene(confirmDialog));
      modalStage.initStyle(StageStyle.UNDECORATED);
      modalStage.initModality(Modality.APPLICATION_MODAL);
      modalStage.initOwner(MainController.primaryStage);
      modalStage.show();

      return;
    } 

    foundWorkOrder.setTechnicianID(loggedTechnician.getUserID());

    loggedTechnician.setStatus(TechnicianStatus.Occupied);
    foundWorkOrder.setWorkOrderState(WorkOrderState.Open);

    DAO.fromUsers().updateInformation(loggedTechnician);
    DAO.fromWorkOrders().updateInformation(foundWorkOrder);

    Parent homeView = FXMLLoader.load(App.class.getResource("views/home.fxml"));

    MainController.mainWindow.setRight(homeView);
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }
}
