package com.gersonfaneto.yams.views.components;

import java.io.IOException;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.controllers.InvoiceDetailsController;
import com.gersonfaneto.yams.controllers.MainController;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.dao.billing.invoice.InvoiceDiskDAO;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.billing.payments.Payment;
import com.gersonfaneto.yams.models.services.Service;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class InvoicesListComponent extends AnchorPane {
  private Invoice targetInvoice;

  public InvoicesListComponent(Invoice targetInvoice) {
    this.targetInvoice = targetInvoice;

    super.getStylesheets().add(App.class.getResource("stylesheets/global.css").toExternalForm());
    super.getStylesheets().clear();
    super.getStyleClass().add("component-item");
    super.setPrefSize(650, 100);

    Label clientNameFieldIndicator = new Label("Cliente");
    Label technicianNameFieldIndicator = new Label("Têcnico");
    Label totalCostFieldIndicator = new Label("Total");
    Label amountPaidFieldIndicator = new Label("Pago");

    clientNameFieldIndicator.setLayoutX(80);
    clientNameFieldIndicator.setLayoutY(15);
    clientNameFieldIndicator.setPrefSize(50, 20);

    technicianNameFieldIndicator.setLayoutX(80);
    technicianNameFieldIndicator.setLayoutY(40);
    technicianNameFieldIndicator.setPrefSize(50, 20);

    totalCostFieldIndicator.setLayoutX(80);
    totalCostFieldIndicator.setLayoutY(65);
    totalCostFieldIndicator.setPrefSize(40, 20);

    amountPaidFieldIndicator.setLayoutX(210);
    amountPaidFieldIndicator.setLayoutY(65);
    amountPaidFieldIndicator.setPrefSize(40, 20);

    Label clientNameField = new Label();
    Label technicianNameField = new Label();
    Label totalCostField = new Label();
    Label amountPaidField = new Label();

    clientNameField.setLayoutX(140);
    clientNameField.setLayoutY(15);
    clientNameField.setPrefSize(400, 20);
    clientNameField.setText(
        DAO.fromClients().findByID(
          DAO.fromWorkOrders().findByID(targetInvoice.getWorkOrderID())
          .getClientID()
        ).getClientName()
    );

    technicianNameField.setLayoutX(140);
    technicianNameField.setLayoutY(40);
    technicianNameField.setPrefSize(400, 20);
    technicianNameField.setText(
        DAO.fromUsers().findByID(
          DAO.fromWorkOrders().findByID(targetInvoice.getWorkOrderID())
          .getTechnicianID()
        ).getUserName()
    );

    totalCostField.setLayoutX(140);
    totalCostField.setLayoutY(65);
    totalCostField.setPrefSize(60, 20);
    totalCostField.setText(
      formatMoney(targetInvoice.getTotalValue())
    );

    amountPaidField.setLayoutX(260);
    amountPaidField.setLayoutY(65);
    amountPaidField.setPrefSize(60, 20);
    amountPaidField.setText(
      formatMoney(
        DAO.fromPayments()
          .findByInvoice(targetInvoice.getInvoiceID())
          .stream()
          .map(Payment::getPaidValue)
          .reduce(0.0, Double::sum)
      )
    );

    super.getChildren().addAll(
        clientNameFieldIndicator,
        technicianNameFieldIndicator,
        totalCostFieldIndicator,
        amountPaidFieldIndicator
    );
    super.getChildren().addAll(
        clientNameField,
        technicianNameField,
        totalCostField,
        amountPaidField
    );

    FontAwesomeIconView updateIcon = new FontAwesomeIconView(FontAwesomeIcon.DOLLAR);

    updateIcon.setLayoutX(25);
    updateIcon.setLayoutY(65);
    updateIcon.setSize("50");

    updateIcon.getStyleClass().add("update-icon");

    updateIcon.setOnMouseClicked(event -> {
      openDetails();
    });

    super.getChildren().add(updateIcon);
  }

  public void openDetails() {
    InvoiceDetailsController detailsController = new InvoiceDetailsController();
    detailsController.setInvoice(targetInvoice);

    FXMLLoader loaderFXML = new FXMLLoader();

    loaderFXML.setLocation(App.class.getResource("views/invoice_details.fxml"));
    loaderFXML.setController(detailsController);

    Parent updateView;
    try {
      updateView = loaderFXML.load();
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    MainController.mainWindow.setRight(updateView);
  }
  
  public String formatMoney(double moneyInput) {
    return String.format("R$ %.2f", moneyInput).replace(".", ",");
  }
}
