module com.example.sleepwell {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sleepwell to javafx.fxml;
    exports com.example.sleepwell;
    exports com.example.sleepwell.database;
    opens com.example.sleepwell.database to javafx.fxml;
}