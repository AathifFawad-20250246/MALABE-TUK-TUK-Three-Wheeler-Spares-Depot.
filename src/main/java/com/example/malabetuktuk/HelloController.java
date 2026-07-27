package com.example.malabetuktuk;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {

    @FXML
    private TableView<Part> inventoryTable;

    @FXML
    private TableColumn<Part, String> codeColumn;

    @FXML
    private TableColumn<Part, String> nameColumn;

    @FXML
    private TableColumn<Part, String> brandColumn;

    @FXML
    private TableColumn<Part, Double> priceColumn;

    @FXML
    private TableColumn<Part, Integer> quantityColumn;

    @FXML
    private TableColumn<Part, String> categoryColumn;

    @FXML
    public void initialize() {

        System.out.println("Controller initialized");

        codeColumn.setCellValueFactory(new PropertyValueFactory<>("partCode"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
    }

    @FXML
    public void loadInventory() {

        inventoryTable.getItems().clear();

        java.util.ArrayList<Part> parts = FileManager.readInventoryFile();

        System.out.println("Parts loaded = " + parts.size());

        inventoryTable.getItems().addAll(parts);

        System.out.println("Rows in table = " + inventoryTable.getItems().size());
    }
}