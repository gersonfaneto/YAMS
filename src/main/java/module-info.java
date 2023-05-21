module com.gersonfaneto.yams {
  requires javafx.controls;
  requires javafx.fxml;

  // Needed for JUnit Test Suite.
  opens com.gersonfaneto.yams.dao;
  opens com.gersonfaneto.yams.dao.billing.invoice;
  opens com.gersonfaneto.yams.dao.billing.paymet;
  opens com.gersonfaneto.yams.dao.entities.client;
  opens com.gersonfaneto.yams.dao.entities.user;
  opens com.gersonfaneto.yams.dao.orders.purchase;
  opens com.gersonfaneto.yams.dao.orders.work;
  opens com.gersonfaneto.yams.dao.services;
  opens com.gersonfaneto.yams.dao.stock;

  // HACK: Needed for making JavaDoc see the whole project. 
  exports com.gersonfaneto.yams.dao;
  exports com.gersonfaneto.yams.dao.billing.invoice;
  exports com.gersonfaneto.yams.dao.billing.paymet;
  exports com.gersonfaneto.yams.dao.entities.client;
  exports com.gersonfaneto.yams.dao.entities.user;
  exports com.gersonfaneto.yams.dao.orders.purchase;
  exports com.gersonfaneto.yams.dao.orders.work;
  exports com.gersonfaneto.yams.dao.services;
  exports com.gersonfaneto.yams.dao.stock;

  exports com.gersonfaneto.yams.exceptions.billing;
  exports com.gersonfaneto.yams.exceptions.client;
  exports com.gersonfaneto.yams.exceptions.orders;
  exports com.gersonfaneto.yams.exceptions.services;
  exports com.gersonfaneto.yams.exceptions.stock;
  exports com.gersonfaneto.yams.exceptions.users;

  exports com.gersonfaneto.yams.models.billing.invoice;
  exports com.gersonfaneto.yams.models.billing.payments;
  exports com.gersonfaneto.yams.models.entities.admnistrator;
  exports com.gersonfaneto.yams.models.entities.client;
  exports com.gersonfaneto.yams.models.entities.receptionist;
  exports com.gersonfaneto.yams.models.entities.technician;
  exports com.gersonfaneto.yams.models.entities.technician.states;
  exports com.gersonfaneto.yams.models.entities.user;

  exports com.gersonfaneto.yams.models.orders.work;
  exports com.gersonfaneto.yams.models.orders.work.states;
  exports com.gersonfaneto.yams.models.orders.purchase;
  
  exports com.gersonfaneto.yams.models.reports;

  exports com.gersonfaneto.yams.models.services;

  exports com.gersonfaneto.yams.models.stock;

  exports com.gersonfaneto.yams.utils;

  // Needed for JavaFX.
  opens com.gersonfaneto.yams to javafx.fxml;

  exports com.gersonfaneto.yams;
}
