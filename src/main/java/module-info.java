module com.example.seniorproject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires  javafx.graphics;
    requires java.sql;

    opens com.example.seniorproject1 to javafx.fxml;
    exports com.example.seniorproject1;
}