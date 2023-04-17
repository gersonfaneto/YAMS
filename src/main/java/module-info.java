module com.gersonfaneto.yams {
  requires javafx.controls;
  requires javafx.fxml;

  opens com.gersonfaneto.yams to
      javafx.fxml;

  exports com.gersonfaneto.yams;
}
