package com.example.malabetuktuk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FileManager.readInventoryFile();
        FileManager.readDealerFile();

        AuditLogger.log("Program Started");

        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 900, 600);

        stage.setTitle("MALABE TUK TUK Inventory System");

        stage.setScene(scene);

        stage.show();
    }
}