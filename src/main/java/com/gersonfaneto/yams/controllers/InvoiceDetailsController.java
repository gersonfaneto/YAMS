package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.models.billing.payments.Payment;
import com.gersonfaneto.yams.models.billing.payments.PaymentMethod;
import com.gersonfaneto.yams.models.entities.user.UserType;
import com.gersonfaneto.yams.utils.TypeParser;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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

  @FXML
  public void closeDetails() {
    methodFilter.getItems().add("Todos");
    for (PaymentMethod paymentMethod : PaymentMethod.values()) {
      methodFilter.getItems().add(TypeParser.parsePaymentMethod(paymentMethod));
    }
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
}

