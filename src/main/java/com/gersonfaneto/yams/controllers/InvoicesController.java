package com.gersonfaneto.yams.controllers;

import java.io.IOException;
import java.util.List;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.views.components.InvoicesListComponent;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InvoicesController {
  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private Label visualFeedback;

  @FXML
  private TextField searchField;

  @FXML
  private ListView<Invoice> listView;

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
          InvoicesListComponent invoiceComponent = new InvoicesListComponent(invoice);

          setGraphic(invoiceComponent);
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
    CreatePaymentController createPaymentController = new CreatePaymentController();
    
    Invoice selectedInvoice = listView.getSelectionModel().getSelectedItem();

    if (selectedInvoice == null) {
      visualFeedback.setText("Selecione uma fatura!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    createPaymentController.setInvoice(selectedInvoice);

    FXMLLoader loaderFXML = new FXMLLoader();

    loaderFXML.setLocation(App.class.getResource("views/create_payment.fxml"));
    loaderFXML.setController(createPaymentController);

    try {
      loaderFXML.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Parent updateView = loaderFXML.getRoot();
    Stage modalStage = new Stage();

    modalStage.setScene(new Scene(updateView));
    modalStage.initStyle(StageStyle.UNDECORATED);
    modalStage.initModality(Modality.APPLICATION_MODAL);
    modalStage.initOwner(MainController.primaryStage);
    modalStage.show();

    MainController.modalStage = modalStage;
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }
}
