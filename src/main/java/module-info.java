module com.gersonfaneto.techinfo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gersonfaneto.techinfo to javafx.fxml;
    exports com.gersonfaneto.techinfo;
}