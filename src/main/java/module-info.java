module com.example.sleepwell {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;

    opens com.example.sleepwell to javafx.fxml;
    exports com.example.sleepwell;
    exports com.example.sleepwell.database;
    opens com.example.sleepwell.database to javafx.fxml;
    exports com.example.sleepwell.controller;
    opens com.example.sleepwell.controller to javafx.fxml;
}