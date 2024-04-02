module com.example.lab03 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.lab03 to javafx.fxml;
    exports com.example.lab03;
}