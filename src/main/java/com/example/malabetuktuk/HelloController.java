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
import javafx.scene.control.Label;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class HelloController {
    private static final int LOW_STOCK_THRESHOLD = 10;

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
    private TextField dealerSearchField;

    @FXML
    private Label lowStockLabel;

    @FXML
    private Label totalPartsLabel;

    @FXML
    private Label totalValueLabel;

    @FXML
    private TextField searchName;

    @FXML
    private TextField searchCategory;

    @FXML
    private TextField searchPrice;

    @FXML
    private TableView<CartItem> cartTable;

    @FXML
    private TableColumn<CartItem, String> cartPartColumn;

    @FXML
    private TableColumn<CartItem, Integer> cartQuantityColumn;

    @FXML
    private TableColumn<CartItem, Double> cartPriceColumn;

    @FXML
    private TextField cartQuantityField;

    @FXML
    private Label cartTotalLabel;

    @FXML
    private TextField discountField;


    private ShoppingCart shoppingCart = new ShoppingCart();

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

        cartPartColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue()
                                .getPart()
                                .getPartName()
                ));


        cartQuantityColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        cellData.getValue()
                                .getQuantity()
                ).asObject());


        cartPriceColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleDoubleProperty(
                        cellData.getValue()
                                .getSubtotal()
                ).asObject());


    }

    @FXML
    public void loadInventory() {

        inventoryTable.getItems().clear();

        java.util.ArrayList<Part> parts = FileManager.readInventoryFile();

        System.out.println("Parts loaded = " + parts.size());

        inventoryTable.getItems().addAll(parts);

        System.out.println("Rows in table = " + inventoryTable.getItems().size());

        showTotalParts();
        showTotalInventoryValue();
    }

    @FXML
    public void groupInventory() {

        inventoryTable.getItems().clear();

        java.util.ArrayList<Part> parts = FileManager.readInventoryFile();

        for (int i = 0; i < parts.size() - 1; i++) {

            for (int j = 0; j < parts.size() - i - 1; j++) {

                Part p1 = parts.get(j);
                Part p2 = parts.get(j + 1);

                int categoryCompare = p1.getCategory().compareToIgnoreCase(p2.getCategory());

                if (categoryCompare > 0 ||
                        (categoryCompare == 0 &&
                                p1.getPartCode().compareToIgnoreCase(p2.getPartCode()) > 0)) {

                    parts.set(j, p2);
                    parts.set(j + 1, p1);

                }
            }
        }

        inventoryTable.getItems().addAll(parts);

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

        dealerTable.getItems().clear();


        ArrayList<Dealer> dealers =
                FileManager.readDealerFile();


        // Shuffle dealers randomly
        java.util.Collections.shuffle(dealers);


        // Display random 4 dealers
        if (dealers.size() >= 4) {

            dealerTable.getItems().addAll(
                    dealers.subList(0, 4)
            );

        } else {

            dealerTable.getItems().addAll(dealers);

        }


        AuditLogger.log("Random 4 Dealers Selected");


        System.out.println("Random 4 Dealers:");

        for (Dealer dealer : dealerTable.getItems()) {

            System.out.println(dealer.getDealerName());

        }

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

            FileManager.saveInventoryFile(inventoryTable.getItems());

            AuditLogger.log("Part Added");

            codeField.clear();
            nameField.clear();
            brandField.clear();
            priceField.clear();
            quantityField.clear();
            categoryField.clear();

        } catch (Exception e) {

            System.out.println("Invalid data!");

        }

        showLowStock();

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

            FileManager.saveInventoryFile(inventoryTable.getItems());

            AuditLogger.log("Part Updated");

        }

        showLowStock();

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

                FileManager.saveInventoryFile(inventoryTable.getItems());

                AuditLogger.log("Part Deleted");

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

        showLowStock();
    }

    @FXML
    public void searchPart() {

        String search = searchField.getText().toLowerCase();

        inventoryTable.getItems().clear();

        for (Part part : FileManager.readInventoryFile()) {

            if (part.getPartCode().toLowerCase().contains(search) ||
                    part.getPartName().toLowerCase().contains(search) ||
                    part.getBrand().toLowerCase().contains(search) ||
                    part.getCategory().toLowerCase().contains(search)) {

                inventoryTable.getItems().add(part);

            }
        }

        AuditLogger.log("Inventory Search");
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

        int count = 0;

        for (Part part : FileManager.readInventoryFile()) {

            if (part.getQuantity() < LOW_STOCK_THRESHOLD) {

                inventoryTable.getItems().add(part);

                count++;

            }

        }

        lowStockLabel.setText("Low Stock Items : " + count);

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

    @FXML
    public void searchDealer() {

        String search = dealerSearchField.getText().toLowerCase();

        dealerTable.getItems().clear();

        for (Dealer dealer : FileManager.readDealerFile()) {

            if (dealer.getDealerId().toLowerCase().contains(search) ||
                    dealer.getDealerName().toLowerCase().contains(search) ||
                    dealer.getPhone().toLowerCase().contains(search) ||
                    dealer.getLocation().toLowerCase().contains(search)) {

                dealerTable.getItems().add(dealer);

            }
        }

        AuditLogger.log("Dealer Search");
    }

    @FXML
    public void showTotalParts() {

        int total = FileManager.readInventoryFile().size();

        totalPartsLabel.setText("Total Parts : " + total);

    }

    @FXML
    public void showTotalInventoryValue() {

        double totalValue = 0;

        for (Part part : FileManager.readInventoryFile()) {

            totalValue += part.getPrice() * part.getQuantity();

        }

        totalValueLabel.setText(
                String.format("Total Inventory Value : Rs. %.2f", totalValue)
        );

    }

    @FXML
    public void addToCart() {


        Part selectedPart =
                inventoryTable.getSelectionModel()
                        .getSelectedItem();


        if (selectedPart == null) {

            System.out.println("Select a part first");

            return;

        }


        int quantity;


        try {

            quantity = Integer.parseInt(
                    cartQuantityField.getText()
            );


        } catch (Exception e) {

            System.out.println("Invalid quantity");

            return;

        }


        shoppingCart.addItem(
                selectedPart,
                quantity
        );

        AuditLogger.log(
                "Added to cart: "
                        + selectedPart.getPartName()
                        + " Quantity: "
                        + quantity
        );


        cartTable.getItems().clear();


        cartTable.getItems().addAll(
                shoppingCart.getItems()
        );


        updateCartTotal();


        AuditLogger.log(
                "Part Added To Cart"
        );

    }

    public void updateCartTotal() {

        double total =
                shoppingCart.getTotal();


        cartTotalLabel.setText(
                String.format(
                        "Total: Rs %.2f",
                        total
                )
        );

    }

    @FXML
    public void clearCart() {

        shoppingCart.clearCart();

        cartTable.getItems().clear();

        cartTotalLabel.setText("Total: Rs.0.00");

    }

    @FXML
    public void applyDiscount() {

        String discountText = discountField.getText().trim();

        if (discountText.isEmpty()) {

            System.out.println("Enter discount percentage");

            return;
        }


        try {

            double discount = Double.parseDouble(discountText);


            double finalTotal =
                    shoppingCart.calculateDiscount(discount);


            cartTotalLabel.setText(
                    "Total after discount: Rs. "
                            + String.format("%.2f", finalTotal)
            );


        } catch (NumberFormatException e) {

            System.out.println("Invalid discount value");

        }

    }

    @FXML
    public void checkout() {

        double finalTotal =
                shoppingCart.calculateCheckoutTotal();


        cartTotalLabel.setText(
                "Final Checkout: Rs. " + finalTotal
        );

        AuditLogger.log(
                "Checkout completed. Total: Rs. "
                        + finalTotal
        );


        System.out.println(
                "Final Checkout: Rs. " + finalTotal
        );

    }
}