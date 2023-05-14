module com.gersonfaneto.yams {
  requires javafx.controls;
  requires javafx.fxml;

  opens com.gersonfaneto.yams to javafx.fxml;

  opens com.gersonfaneto.yams.dao;
  opens com.gersonfaneto.yams.dao.billing.invoice;
  opens com.gersonfaneto.yams.dao.billing.paymet;
  opens com.gersonfaneto.yams.dao.entities.client;
  opens com.gersonfaneto.yams.dao.entities.user;
  opens com.gersonfaneto.yams.dao.orders.purchase;
  opens com.gersonfaneto.yams.dao.orders.work;
  opens com.gersonfaneto.yams.dao.services;
  opens com.gersonfaneto.yams.dao.stock;

  exports com.gersonfaneto.yams;
}
