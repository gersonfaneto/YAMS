package com.gersonfaneto.yams.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.entities.technician.TechnicianStatus;
import com.gersonfaneto.yams.models.services.order.WorkOrder;
import com.gersonfaneto.yams.models.services.order.WorkOrderState;
import com.gersonfaneto.yams.models.services.service.Service;
import com.gersonfaneto.yams.models.services.service.ServiceType;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.utils.Time;
import com.gersonfaneto.yams.views.components.ComponentSize;
import com.gersonfaneto.yams.views.components.ServicesListComponent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
  public void handleClicks(ActionEvent actionEvent) throws IOException {
    boolean allFinished = DAO.fromService()
      .findByWorkOrder(openOrder.getWorkOrderID())
      .stream()
      .map(Service::isComplete)
      .reduce(true, (a, b) -> a && b);

    if (actionEvent.getSource() == cancelOrderButton) {
      if (allFinished) {
        String confirmationMessage = "Todos os serviços já foram realizados, seleciona a opção \"Finalizar Ordem\"!";
        
        MainController.openModal(confirmationMessage, false); 
      }
      else {
        String confirmationMessage = "Deseja mesmo cancelar a ordem?";

        MainController.openModal(confirmationMessage, true);

        if (MainController.isConfirmed) {
          List<Service> relatedServices = DAO.fromService()
            .findByWorkOrder(openOrder.getWorkOrderID());

          for (Service currentService : relatedServices) {
            if (!currentService.isComplete()) {
              if (currentService.getServiceType() == ServiceType.Assembly) {
                Component usedComponent = currentService.getUsedComponent();
                Component foundComponent = DAO.fromComponents().findEquals(usedComponent);

                if (foundComponent != null) {
                  foundComponent.setAmountInStock(
                      foundComponent.getAmountInStock() + currentService.getAmountUsed()
                      );
                  DAO.fromComponents().updateInformation(foundComponent);
                }
                else {
                  DAO.fromComponents().createOne(usedComponent);
                }
              }
              DAO.fromService().deleteByID(currentService.getServiceID());
            }
          }

          openOrder.setWorkOrderState(WorkOrderState.Canceled);
          openOrder.setClosedAt(Calendar.getInstance());
          loggedTechnician.setStatus(TechnicianStatus.Free);

          DAO.fromWorkOrders().updateInformation(openOrder);
          DAO.fromUsers().updateInformation(loggedTechnician);

          double orderTotalValue = DAO.fromService()
            .findByWorkOrder(openOrder.getWorkOrderID())
            .stream()
            .map(Service::getServicePrice)
            .reduce(0.0, Double::sum);

          if (orderTotalValue != 0) {
            Invoice newInvoice = new Invoice(
                openOrder.getWorkOrderID(),
                DAO.fromService().findByWorkOrder(openOrder.getWorkOrderID())
                .stream()
                .map(Service::getServicePrice)
                .reduce(0.0, Double::sum)
                );

            DAO.fromInvoices().createOne(newInvoice);
          }

          Parent homeView = FXMLLoader.load(App.class.getResource("views/home/Main.fxml"));

          MainController.mainWindow.setRight(homeView);
        }
      }
    }
    else {
      if (allFinished) {
        openOrder.setWorkOrderState(WorkOrderState.Finished);
        openOrder.setClosedAt(Calendar.getInstance());
        loggedTechnician.setStatus(TechnicianStatus.Free);

        DAO.fromWorkOrders().updateInformation(openOrder);
        DAO.fromUsers().updateInformation(loggedTechnician);

        double orderTotalValue = DAO.fromService()
          .findByWorkOrder(openOrder.getWorkOrderID())
          .stream()
          .map(Service::getServicePrice)
          .reduce(0.0, Double::sum);

        if (orderTotalValue != 0) {
          Invoice newInvoice = new Invoice(
              openOrder.getWorkOrderID(),
              DAO.fromService().findByWorkOrder(openOrder.getWorkOrderID())
              .stream()
              .map(Service::getServicePrice)
              .reduce(0.0, Double::sum)
              );

          DAO.fromInvoices().createOne(newInvoice);
        }

        Parent homeView = FXMLLoader.load(App.class.getResource("views/home/Main.fxml"));

        MainController.mainWindow.setRight(homeView);
      }
      else {
        String confirmationMessage = "Ainda há serviços em aberto!";

        MainController.openModal(confirmationMessage, false);
      }
    }
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

