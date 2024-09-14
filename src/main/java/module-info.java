module com.example.portsytemca1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;
    requires java.logging;
    opens Classes to xstream;
//
    opens com.example.portsytemca1 to javafx.fxml;
    exports com.example.portsytemca1;
}