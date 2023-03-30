module com.gersonfaneto.techinfo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gersonfaneto.yams to javafx.fxml;
    exports com.gersonfaneto.yams;
}