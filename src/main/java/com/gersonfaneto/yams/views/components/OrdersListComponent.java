package com.gersonfaneto.yams.views.components;

import java.io.IOException;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.controllers.MainController;
import com.gersonfaneto.yams.controllers.OrderDetailsController;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.utils.Time;
import com.gersonfaneto.yams.utils.TypeParser;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class OrdersListComponent extends AnchorPane {
  private WorkOrder targetOrder;
  private ObservableList<WorkOrder> workOrdersList;

  public OrdersListComponent(
      WorkOrder workOrder,
      ObservableList<WorkOrder> workOrdersLists
  ) {
    this.targetOrder = workOrder;
    this.workOrdersList = workOrdersList;

    super.getStylesheets().add(App.class.getResource("stylesheets/global.css").toExternalForm());
    super.getStylesheets().clear();
    super.getStyleClass().add("component-item");

    super.setMinSize(650, 100);
    super.setPrefSize(650, 100);
    super.setMaxSize(650, 100);

    FontAwesomeIconView ordersIcon = new FontAwesomeIconView(FontAwesomeIcon.CLIPBOARD); 

    ordersIcon.setLayoutX(25);
    ordersIcon.setLayoutY(70);
    ordersIcon.setSize("50");
    ordersIcon.getStyleClass().add("order-icon");

    ordersIcon.setOnMouseClicked(event -> {
      viewDetails();
    });

    Label clientNameField = new Label();
    Label openingDateField = new Label();
    Label closingDateField = new Label();
    Label priceField = new Label();
    Label statusField = new Label();

    clientNameField.setLayoutX(90);
    clientNameField.setLayoutY(15);
    clientNameField.setPrefSize(400, 20);
    clientNameField.setText(
        DAO.fromClients().findByID(workOrder.getClientID()).getClientName()
    );

    openingDateField.setLayoutX(190);
    openingDateField.setLayoutY(40);
    openingDateField.setPrefSize(90, 20);
    openingDateField.setText(
      Time.extractDateFromCalendar(workOrder.getCreatedAt())
    );

    closingDateField.setLayoutX(190);
    closingDateField.setLayoutY(65);
    closingDateField.setPrefSize(90, 20);
    if (workOrder.getClosedAt() == null) {
      closingDateField.setText("");
    }
    else {
      openingDateField.setText(
          Time.extractDateFromCalendar(workOrder.getClosedAt())
      );
    }

    priceField.setLayoutX(390);
    priceField.setLayoutY(40);
    priceField.setPrefSize(90, 20);
    priceField.setText(
        formatMoney(
          DAO.fromService()
          .findByWorkOrder(workOrder.getWorkOrderID())
          .stream()
          .map(service -> service.getServicePrice())
          .reduce(Double::sum)
          .orElse(0.0)
        )
    );

    statusField.setLayoutX(390);
    statusField.setLayoutY(65);
    statusField.setPrefSize(90, 20);
    statusField.setText(
        TypeParser.parseWorkOrderStateType(workOrder.getWorkOrderState())
    );

    Label openingDateFieldIndicator = new Label("Abertura");
    Label closingDateFieldIndicator = new Label("Fechamento");
    Label priceFieldIndicator = new Label("Preço");
    Label statusFieldIndicator = new Label("Status");

    openingDateFieldIndicator.setLayoutX(90);
    openingDateFieldIndicator.setLayoutY(40);
    openingDateFieldIndicator.setPrefSize(90, 20);

    closingDateFieldIndicator.setLayoutX(90);
    closingDateFieldIndicator.setLayoutY(65);
    closingDateFieldIndicator.setPrefSize(90, 20);

    priceFieldIndicator.setLayoutX(290);
    priceFieldIndicator.setLayoutY(40);
    priceFieldIndicator.setPrefSize(90, 20);

    statusFieldIndicator.setLayoutX(290);
    statusFieldIndicator.setLayoutY(65);
    statusFieldIndicator.setPrefSize(90, 20);

    super.getChildren().add(ordersIcon);
    super.getChildren().addAll(
        clientNameField,
        openingDateField,
        closingDateField,
        priceField,
        statusField
    );
    super.getChildren().addAll(
        openingDateFieldIndicator,
        closingDateFieldIndicator,
        priceFieldIndicator,
        statusFieldIndicator
    );
  }

  private String formatMoney(double moneyInput) {
    return String.format("R$ %.2f", moneyInput).replace(".", ",");
  }

  private void viewDetails() {
    OrderDetailsController detailsController = new OrderDetailsController();
    detailsController.setWorkOrder(targetOrder);

    FXMLLoader loaderFXML = new FXMLLoader();
    loaderFXML.setLocation(App.class.getResource("views/order_details.fxml"));

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
}
