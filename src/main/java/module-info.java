module com.example.malabetuktuk {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.malabetuktuk to javafx.fxml;
    exports com.example.malabetuktuk;
}