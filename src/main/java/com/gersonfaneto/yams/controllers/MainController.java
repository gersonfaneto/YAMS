package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.dao.Persist;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.services.order.WorkOrder;
import com.gersonfaneto.yams.views.windows.ActionConfirmationDialog;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class MainController {

  public static User loggedUser;
  public static WorkOrder openOrder;
  public static BorderPane mainWindow;
  public static BorderPane homeWindow;
  public static Stage modalStage;
  public static Stage primaryStage;
  public static boolean isConfirmed = false;

  public static void saveData() {
    ((Persist) DAO.fromUsers()).saveAll();
    ((Persist) DAO.fromClients()).saveAll();
    ((Persist) DAO.fromPayments()).saveAll();
    ((Persist) DAO.fromComponents()).saveAll();
    ((Persist) DAO.fromService()).saveAll();
    ((Persist) DAO.fromWorkOrders()).saveAll();
  }

  public static void loadData() {
    ((Persist) DAO.fromUsers()).loadAll();
    ((Persist) DAO.fromClients()).loadAll();
    ((Persist) DAO.fromPayments()).loadAll();
    ((Persist) DAO.fromComponents()).loadAll();
    ((Persist) DAO.fromService()).loadAll();
    ((Persist) DAO.fromWorkOrders()).loadAll();
  }

  public static void openModal(String modalMessage, boolean awaitResponse) {
    ActionConfirmationDialog confirmDialog = new ActionConfirmationDialog(modalMessage);

    Stage modalStage = new Stage();

    MainController.modalStage = modalStage;

    modalStage.setScene(new Scene(confirmDialog));
    modalStage.initStyle(StageStyle.UNDECORATED);
    modalStage.initModality(Modality.APPLICATION_MODAL);
    modalStage.initOwner(MainController.primaryStage);

    if (awaitResponse) {
      modalStage.showAndWait();
    }
    else {
      modalStage.show();
    }
  }
}
