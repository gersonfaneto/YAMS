package com.gersonfaneto.yams.controllers;

import java.io.IOException;
import java.util.List;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.billing.payment.Payment;
import com.gersonfaneto.yams.models.billing.payment.PaymentMethod;
import com.gersonfaneto.yams.utils.TypeParser;
import com.gersonfaneto.yams.views.components.PaymentsListComponent;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class InvoiceDetailsController {
  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private FontAwesomeIconView backButton;

  @FXML
  private ListView<Payment> listView;

  @FXML
  private ComboBox<String> methodFilter;

  @FXML
  private TextField searchField;

  @FXML
  private TextField clientNameField;

  @FXML
  private TextField technicianNameField;

  private ObservableList<Payment> paymentsList;
  private FilteredList<Payment> filteredPayments;

  private Invoice targetInvoice;

  @FXML
  public void initialize() {
    clientNameField.setText(
        DAO.fromClients().findByID(
          DAO.fromWorkOrders().findByID(
            targetInvoice.getWorkOrderID()
          ).getClientID()
        ).getClientName()
    );

    technicianNameField.setText(
        DAO.fromUsers().findByID(
          DAO.fromWorkOrders().findByID(
            targetInvoice.getWorkOrderID()
          ).getTechnicianID()
        ).getUserName()
    );

    methodFilter.getItems().add("Todos");
    for (PaymentMethod paymentMethod : PaymentMethod.values()) {
      methodFilter.getItems().add(TypeParser.parsePaymentMethod(paymentMethod));
    }

    paymentsList = FXCollections.observableArrayList();
    filteredPayments = new FilteredList<>(paymentsList, x -> true);

    listView.setCellFactory(listView -> new ListCell<Payment>() {
      @Override
      protected void updateItem(Payment payment, boolean empty) {
        super.updateItem(payment, empty);

        if (payment == null || empty) {
          setGraphic(null);
        } else {
          PaymentsListComponent paymentComponent = new PaymentsListComponent(payment);

          setGraphic(paymentComponent);
        }
      }
    });

    List<Payment> relatedPayments = DAO.fromPayments()
      .findByInvoice(targetInvoice.getInvoiceID());

    paymentsList.addAll(relatedPayments);

    SortedList<Payment> sortedPayments = new SortedList<>(filteredPayments);

    listView.setItems(sortedPayments);
  }

  @FXML
  public void filterSearch() {
    listView.setItems(filteredPayments.filtered(payment -> {
      String methodValue = methodFilter.getValue();

      PaymentMethod paymentMethod = TypeParser.parsePaymentMethod(methodValue);

      return paymentMethod == null || payment.getPaymentMethod() == paymentMethod;
    }));
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }

  @FXML
  public void closeDetails() throws IOException {
    Parent invoicesView = FXMLLoader.load(App.class.getResource("views/invoices/Main.fxml"));

    MainController.mainWindow.setRight(invoicesView);
  }

  public void setInvoice(Invoice targetInvoice) {
    this.targetInvoice = targetInvoice;
  }
  
  public Invoice getInvoice() {
    return targetInvoice;
  }
}

