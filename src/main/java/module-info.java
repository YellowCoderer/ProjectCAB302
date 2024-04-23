module com.example.sleepwell {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sleepwell to javafx.fxml;
    exports com.example.sleepwell;
}