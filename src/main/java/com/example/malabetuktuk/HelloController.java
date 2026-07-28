package com.example.malabetuktuk;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.*;
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
    private javafx.scene.control.TextField codeField;

    @FXML
    private javafx.scene.control.TextField nameField;

    @FXML
    private javafx.scene.control.TextField brandField;

    @FXML
    private javafx.scene.control.TextField priceField;

    @FXML
    private javafx.scene.control.TextField quantityField;

    @FXML
    private javafx.scene.control.TextField categoryField;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Dealer> dealerTable;

    @FXML
    private TableColumn<Dealer, String> dealerIdColumn;

    @FXML
    private TableColumn<Dealer, String> dealerNameColumn;

    @FXML
    private TableColumn<Dealer, String> phoneColumn;

    @FXML
    private TableColumn<Dealer, String> locationColumn;

    @FXML
    private TextField dealerIdField;

    @FXML
    private TextField dealerNameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField locationField;

    @FXML
    public void initialize() {

        codeColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getPartCode()));

        nameColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getPartName()));

        brandColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getBrand()));

        priceColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleDoubleProperty(
                        cellData.getValue().getPrice()).asObject());

        quantityColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        cellData.getValue().getQuantity()).asObject());

        categoryColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getCategory()));

        dealerIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("dealerId"));

        dealerNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("dealerName"));

        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phone"));

        locationColumn.setCellValueFactory(
                new PropertyValueFactory<>("location"));
    }

    @FXML
    public void loadInventory() {

        inventoryTable.getItems().clear();

        java.util.ArrayList<Part> parts = FileManager.readInventoryFile();

        System.out.println("Parts loaded = " + parts.size());

        inventoryTable.getItems().addAll(parts);

        System.out.println("Rows in table = " + inventoryTable.getItems().size());
    }

    @FXML
    public void loadDealers() {

        dealerTable.getItems().clear();

        dealerTable.getItems().addAll(
                FileManager.readDealerFile()
        );

        AuditLogger.log("Dealer list loaded");

    }

    @FXML
    public void randomDealer() {

        if (dealerTable.getItems().isEmpty()) {

            loadDealers();

        }

        int random = (int) (Math.random() * dealerTable.getItems().size());

        dealerTable.getSelectionModel().select(random);

        Dealer dealer = dealerTable.getSelectionModel().getSelectedItem();

        System.out.println("Selected Dealer: " + dealer.getDealerName());

    }

    @FXML
    public void addPart() {

        try {

            Part part = new Part(

                    codeField.getText(),

                    nameField.getText(),

                    brandField.getText(),

                    Double.parseDouble(priceField.getText()),

                    Integer.parseInt(quantityField.getText()),

                    categoryField.getText(),

                    "",

                    ""

            );

            inventoryTable.getItems().add(part);

            codeField.clear();
            nameField.clear();
            brandField.clear();
            priceField.clear();
            quantityField.clear();
            categoryField.clear();

        } catch (Exception e) {

            System.out.println("Invalid data!");

        }

    }

    @FXML
    public void selectPart() {

        Part part = inventoryTable.getSelectionModel().getSelectedItem();

        if (part != null) {

            codeField.setText(part.getPartCode());
            nameField.setText(part.getPartName());
            brandField.setText(part.getBrand());
            priceField.setText(String.valueOf(part.getPrice()));
            quantityField.setText(String.valueOf(part.getQuantity()));
            categoryField.setText(part.getCategory());

        }
    }

    @FXML
    public void updatePart() {

        Part part = inventoryTable.getSelectionModel().getSelectedItem();

        if (part != null) {

            part.setPartName(nameField.getText());
            part.setBrand(brandField.getText());
            part.setPrice(Double.parseDouble(priceField.getText()));
            part.setQuantity(Integer.parseInt(quantityField.getText()));
            part.setCategory(categoryField.getText());

            inventoryTable.refresh();

        }

    }

    @FXML
    public void deletePart() {

        Part selectedPart = inventoryTable.getSelectionModel().getSelectedItem();

        if (selectedPart != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Part");
            alert.setHeaderText("Delete Selected Part?");
            alert.setContentText("Are you sure?");

            if (alert.showAndWait().get() == ButtonType.OK) {

                inventoryTable.getItems().remove(selectedPart);

            }

            codeField.clear();
            nameField.clear();
            brandField.clear();
            priceField.clear();
            quantityField.clear();
            categoryField.clear();

            AuditLogger.log("Deleted Part : " + selectedPart.getPartCode());

        } else {

            System.out.println("Please select a part to delete.");

        }
    }

    @FXML
    public void searchPart() {

        String keyword = searchField.getText().toLowerCase();

        inventoryTable.getItems().clear();

        for (Part part : FileManager.readInventoryFile()) {

            if (part.getPartCode().toLowerCase().contains(keyword)
                    || part.getPartName().toLowerCase().contains(keyword)
                    || part.getBrand().toLowerCase().contains(keyword)
                    || part.getCategory().toLowerCase().contains(keyword)) {

                inventoryTable.getItems().add(part);

            }

        }

    }

    @FXML
    public void sortParts() {

        javafx.collections.ObservableList<Part> list = inventoryTable.getItems();

        for (int i = 0; i < list.size() - 1; i++) {

            for (int j = 0; j < list.size() - i - 1; j++) {

                if (list.get(j).getPartCode()
                        .compareTo(list.get(j + 1).getPartCode()) > 0) {

                    Part temp = list.get(j);

                    list.set(j, list.get(j + 1));

                    list.set(j + 1, temp);

                }

            }

        }

        inventoryTable.refresh();

        AuditLogger.log("Inventory Sorted");

    }

    @FXML
    public void showLowStock() {

        inventoryTable.getItems().clear();

        for (Part part : FileManager.readInventoryFile()) {

            if (part.getQuantity() < 5) {

                inventoryTable.getItems().add(part);

            }

        }

        AuditLogger.log("Viewed Low Stock Parts");

    }

    @FXML
    public void addDealer() {

        Dealer dealer = new Dealer(

                dealerIdField.getText(),

                dealerNameField.getText(),

                phoneField.getText(),

                locationField.getText()

        );

        dealerTable.getItems().add(dealer);

        FileManager.saveDealerFile(dealerTable.getItems());

        AuditLogger.log("Dealer Added");

        dealerIdField.clear();
        dealerNameField.clear();
        phoneField.clear();
        locationField.clear();

    }

    @FXML
    public void updateDealer() {

        Dealer dealer = dealerTable.getSelectionModel().getSelectedItem();

        if (dealer == null) {

            return;

        }

        dealer.setDealerName(dealerNameField.getText());

        dealer.setPhoneNumber(phoneField.getText());

        dealer.setLocation(locationField.getText());

        dealerTable.refresh();

        FileManager.saveDealerFile(dealerTable.getItems());

        AuditLogger.log("Dealer Updated");

    }

    @FXML
    public void deleteDealer() {

        Dealer dealer = dealerTable.getSelectionModel().getSelectedItem();

        if (dealer != null) {

            dealerTable.getItems().remove(dealer);

            FileManager.saveDealerFile(dealerTable.getItems());

            AuditLogger.log("Dealer Deleted");

        }

    }

    @FXML
    public void selectDealer() {

        Dealer dealer = dealerTable.getSelectionModel().getSelectedItem();

        if (dealer == null) {

            return;

        }

        dealerIdField.setText(dealer.getDealerId());

        dealerNameField.setText(dealer.getDealerName());

        phoneField.setText(dealer.getPhone());

        locationField.setText(dealer.getLocation());

    }
}