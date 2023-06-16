package com.gersonfaneto.yams.controllers;

import java.io.IOException;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.billing.payments.Payment;
import com.gersonfaneto.yams.models.billing.payments.PaymentMethod;
import com.gersonfaneto.yams.models.entities.client.Client;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.utils.TypeParser;
import com.gersonfaneto.yams.views.components.PaymentsListComponent;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class CreatePaymentController {
  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private Label visualFeedback;

  @FXML
  private TextField responsibleNameField;

  @FXML
  private TextField clientNameField;

  @FXML
  private ComboBox<String> paymentMethodSelector;

  @FXML
  private TextField paymentValueField;

  @FXML
  private Button cancelButton;

  @FXML
  private Button confirmButton;

  private Invoice targetInvoice;

  @FXML
  public void initialize() {
    for (PaymentMethod paymentMethod : PaymentMethod.values()) {
      paymentMethodSelector.getItems().add(
          TypeParser.parsePaymentMethod(paymentMethod)
      );
    } 

    double amountRemaining = DAO.fromPayments()
      .findByInvoice(targetInvoice.getInvoiceID())
      .stream()
      .map(Payment::getPaidValue)
      .reduce(0.0, Double::sum);

    WorkOrder relatedWorkOrder = DAO.fromWorkOrders()
      .findByID(targetInvoice.getWorkOrderID());

    Client relatedClient = DAO.fromClients()
      .findByID(relatedWorkOrder.getClientID());
    
    Technician relatedTechnician = (Technician) DAO.fromUsers()
      .findByID(relatedWorkOrder.getTechnicianID());
    
    paymentValueField.setText(formatMoney(
      targetInvoice.getTotalValue() - amountRemaining
    ));

    responsibleNameField.setText(relatedTechnician.getUserName());
    clientNameField.setText(relatedClient.getClientName());
  }

  @FXML
  public void handleClicks(ActionEvent actionEvent) throws IOException {
    if (actionEvent.getSource() == confirmButton) {
      String paymentMethod = paymentMethodSelector.getValue();
      double paidValue = getPaidValue();

      double currentlyPaid = DAO.fromPayments()
        .findByInvoice(targetInvoice.getInvoiceID())
        .stream()
        .map(Payment::getPaidValue)
        .reduce(0.0, Double::sum);

      if (paymentMethod == null) {
        visualFeedback.setText("Selecione o método de pagamento!"); 
        visualFeedback.setTextFill(Color.RED);
        return;
      }

      if (paidValue == -1) {
        visualFeedback.setText("Valor de pagament inválido!");
        visualFeedback.setTextFill(Color.RED);
        return;
      }

      if (paidValue > targetInvoice.getTotalValue() + currentlyPaid) {
        visualFeedback.setText("Valor de pagamento excede o total da fatura!");
        visualFeedback.setTextFill(Color.RED);
        return;
      }

      Payment newPayment = new Payment(
          targetInvoice.getInvoiceID(),
          TypeParser.parsePaymentMethod(paymentMethod),
          paidValue
      );

      DAO.fromPayments().createOne(newPayment);
    }

    closeWindow();
  }

  @FXML
  public void closeWindow() throws IOException {
    Parent invoicesView = FXMLLoader.load(App.class.getResource("views/invoices.fxml"));

    MainController.mainWindow.setRight(invoicesView);

    MainController.modalStage.close();
  }

  private String formatMoney(double moneyInput) {
    return String.format("%.2f", moneyInput).replace(".", ",");
  }

  public double getPaidValue() {
    try {
      double priceValue = Double.parseDouble(
          paymentValueField.getText().replaceFirst(",", ".")
      );

      return (priceValue > 0) ? priceValue : -1;
    } catch (NumberFormatException nfe) {
      return -1;
    }
  }

  public void setInvoice(Invoice targetInvoice) {
    this.targetInvoice = targetInvoice; 
  }

  public Invoice getInvoice() {
    return targetInvoice;
  }
}

