module com.gersonfaneto.techinfo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gersonfaneto.techinfo to javafx.fxml;
    exports com.gersonfaneto.techinfo;
    exports com.gersonfaneto.techinfo.controllers;
    opens com.gersonfaneto.techinfo.controllers to javafx.fxml;
}