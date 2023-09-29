module com.example.homework_03 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.homework_03 to javafx.fxml;
    exports com.example.homework_03;
}