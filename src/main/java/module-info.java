module com.gersonfaneto.yams {
  requires javafx.graphics;
  requires javafx.controls;
  requires javafx.fxml;
  requires de.jensd.fx.glyphs.fontawesome;
  requires de.jensd.fx.glyphs.commons;

  // Needed for JUnit Test Suite.
  opens com.gersonfaneto.yams.dao;
  opens com.gersonfaneto.yams.dao.billing.invoice;
  opens com.gersonfaneto.yams.dao.billing.payment;
  opens com.gersonfaneto.yams.dao.entities.client;
  opens com.gersonfaneto.yams.dao.entities.user;
  opens com.gersonfaneto.yams.dao.services.service;
  opens com.gersonfaneto.yams.dao.services.order;
  opens com.gersonfaneto.yams.dao.stock;

  // HACK: Needed for making JavaDoc see the whole project.
  exports com.gersonfaneto.yams.dao;
  exports com.gersonfaneto.yams.dao.billing.invoice;
  exports com.gersonfaneto.yams.dao.billing.payment;
  exports com.gersonfaneto.yams.dao.entities.client;
  exports com.gersonfaneto.yams.dao.entities.user;
  exports com.gersonfaneto.yams.dao.services.service;
  exports com.gersonfaneto.yams.dao.services.order;
  exports com.gersonfaneto.yams.dao.stock;
  exports com.gersonfaneto.yams.models.billing.invoice;
  exports com.gersonfaneto.yams.models.billing.payment;
  exports com.gersonfaneto.yams.models.entities.admnistrator;
  exports com.gersonfaneto.yams.models.entities.client;
  exports com.gersonfaneto.yams.models.entities.receptionist;
  exports com.gersonfaneto.yams.models.entities.technician;
  exports com.gersonfaneto.yams.models.entities.user;
  exports com.gersonfaneto.yams.models.services.service;
  exports com.gersonfaneto.yams.models.services.order;
  exports com.gersonfaneto.yams.models.stock;
  exports com.gersonfaneto.yams.utils;

  // Needed for JavaFX.
  opens com.gersonfaneto.yams.controllers to
      javafx.fxml,
      javafx.controls,
      javafx.graphics;

  exports com.gersonfaneto.yams;
}
