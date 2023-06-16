package com.gersonfaneto.yams.controllers;

import java.util.List;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.views.components.OrdersListComponent;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class InvoicesController {
  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private ListView<Invoice> listView;

  @FXML
  private TextField searchField;

  private ObservableList<Invoice> invoicesList;
  private FilteredList<Invoice> filteredInvoices;

  @FXML
  public void initialize() {
    invoicesList = FXCollections.observableArrayList();
    filteredInvoices = new FilteredList<>(invoicesList);

    listView.setCellFactory(listView -> new ListCell<Invoice>() {
      @Override
      protected void updateItem(Invoice invoice, boolean empty) {
        super.updateItem(invoice, empty);

        if (invoice == null || empty) {
          setGraphic(null);
        }
        else {
        }
      }
    });

    List<Invoice> allInvoices = DAO.fromInvoices().findMany();

    invoicesList.addAll(allInvoices);

    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredInvoices.setPredicate(service -> {
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if (
            DAO.fromClients().findByID(
              DAO.fromWorkOrders().findByID(
                service.getWorkOrderID()
              ).getClientID()
            ).getClientName().toLowerCase().indexOf(lowerCaseFilter) != -1
        ) {
          return true;
        }
        else {
          return false;
        }
      });
    });

    SortedList<Invoice> sortedInvoices = new SortedList<>(filteredInvoices);

    listView.setItems(sortedInvoices);
  }

  @FXML
  public void newPayment() {

  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }
}
