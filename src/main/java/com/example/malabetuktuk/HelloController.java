package com.example.malabetuktuk;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class HelloController {

    // =========================
    // LOW STOCK
    // =========================

    private static final int LOW_STOCK_THRESHOLD = 10;


    // =========================
    // INVENTORY TABLE
    // =========================

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


    // =========================
    // INVENTORY FIELDS
    // =========================

    @FXML
    private TextField codeField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField searchField;


    // =========================
    // DEALER TABLE
    // =========================

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


    // =========================
    // DEALER FIELDS
    // =========================

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


    // =========================
    // LABELS
    // =========================

    @FXML
    private Label lowStockLabel;

    @FXML
    private Label totalPartsLabel;

    @FXML
    private Label totalValueLabel;


    // =========================
    // MULTI CRITERIA SEARCH
    // =========================

    @FXML
    private TextField searchName;

    @FXML
    private TextField searchCategory;

    @FXML
    private TextField searchPrice;


    // =========================
    // CART
    // =========================

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


    // =========================================================
    // INITIALIZE
    // =========================================================

    @FXML
    public void initialize() {

        // Inventory columns

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
                        cellData.getValue().getPrice()
                ).asObject());

        quantityColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(
                        cellData.getValue().getQuantity()
                ).asObject());

        categoryColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getCategory()));


        // Dealer columns

        dealerIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("dealerId"));

        dealerNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("dealerName"));

        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phone"));

        locationColumn.setCellValueFactory(
                new PropertyValueFactory<>("location"));


        // Cart columns

        cartPartColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue()
                                .getPart()
                                .getPartName()));

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


    // =========================================================
    // LOAD INVENTORY
    // =========================================================

    @FXML
    public void loadInventory() {

        inventoryTable.getItems().clear();

        ArrayList<Part> parts =
                FileManager.readInventoryFile();

        inventoryTable.getItems().addAll(parts);

        showTotalParts();
        showTotalInventoryValue();
        updateLowStockCount();

        System.out.println("Parts loaded = " + parts.size());
        System.out.println(
                "Rows in table = "
                        + inventoryTable.getItems().size());
    }


    // =========================================================
    // GROUP INVENTORY
    // Category → Part Code
    // =========================================================

    @FXML
    public void groupInventory() {

        inventoryTable.getItems().clear();

        ArrayList<Part> parts =
                FileManager.readInventoryFile();


        // Bubble sort

        for (int i = 0;
             i < parts.size() - 1;
             i++) {

            for (int j = 0;
                 j < parts.size() - i - 1;
                 j++) {

                Part p1 = parts.get(j);
                Part p2 = parts.get(j + 1);

                int categoryCompare =
                        p1.getCategory()
                                .compareToIgnoreCase(
                                        p2.getCategory());


                if (categoryCompare > 0 ||
                        (categoryCompare == 0 &&
                                p1.getPartCode()
                                        .compareToIgnoreCase(
                                                p2.getPartCode()) > 0)) {

                    parts.set(j, p2);
                    parts.set(j + 1, p1);
                }
            }
        }

        inventoryTable.getItems().addAll(parts);
    }


    // =========================================================
    // ADD PART
    // =========================================================

    @FXML
    public void addPart() {

        try {

            String code =
                    codeField.getText().trim();

            String name =
                    nameField.getText().trim();

            String brand =
                    brandField.getText().trim();

            String priceText =
                    priceField.getText().trim();

            String quantityText =
                    quantityField.getText().trim();

            String category =
                    categoryField.getText().trim();


            if (code.isEmpty() ||
                    name.isEmpty() ||
                    priceText.isEmpty() ||
                    quantityText.isEmpty() ||
                    category.isEmpty()) {

                showMessage(
                        "Invalid Input",
                        "Please fill all required fields.");

                return;
            }


            double price =
                    Double.parseDouble(priceText);

            int quantity =
                    Integer.parseInt(quantityText);


            if (price < 0 || quantity < 0) {

                showMessage(
                        "Invalid Input",
                        "Price and quantity cannot be negative.");

                return;
            }


            // Check duplicate code

            for (Part existingPart :
                    inventoryTable.getItems()) {

                if (existingPart.getPartCode()
                        .equalsIgnoreCase(code)) {

                    showMessage(
                            "Duplicate Part",
                            "This part code already exists.");

                    return;
                }
            }


            Part part = new Part(
                    code,
                    name,
                    brand,
                    price,
                    quantity,
                    category,
                    "",
                    ""
            );


            inventoryTable.getItems().add(part);


            FileManager.saveInventoryFile(
                    inventoryTable.getItems());


            AuditLogger.log(
                    "Part Added : " + code);


            codeField.clear();
            nameField.clear();
            brandField.clear();
            priceField.clear();
            quantityField.clear();
            categoryField.clear();


            showTotalParts();
            showTotalInventoryValue();
            updateLowStockCount();


            System.out.println(
                    "Part added successfully: "
                            + code);

        } catch (NumberFormatException e) {

            showMessage(
                    "Invalid Input",
                    "Price and quantity must be numbers.");

        } catch (Exception e) {

            showMessage(
                    "Error",
                    "Could not add the part.");
        }
    }


    // =========================================================
    // SELECT PART
    // =========================================================

    @FXML
    public void selectPart() {

        Part part =
                inventoryTable
                        .getSelectionModel()
                        .getSelectedItem();


        if (part != null) {

            codeField.setText(
                    part.getPartCode());

            nameField.setText(
                    part.getPartName());

            brandField.setText(
                    part.getBrand());

            priceField.setText(
                    String.valueOf(
                            part.getPrice()));

            quantityField.setText(
                    String.valueOf(
                            part.getQuantity()));

            categoryField.setText(
                    part.getCategory());
        }
    }


    // =========================================================
    // UPDATE PART
    // =========================================================

    @FXML
    public void updatePart() {

        Part part =
                inventoryTable
                        .getSelectionModel()
                        .getSelectedItem();


        if (part == null) {

            showMessage(
                    "Update Part",
                    "Please select a part first.");

            return;
        }


        try {

            double price =
                    Double.parseDouble(
                            priceField.getText());

            int quantity =
                    Integer.parseInt(
                            quantityField.getText());


            if (price < 0 || quantity < 0) {

                showMessage(
                        "Invalid Input",
                        "Price and quantity cannot be negative.");

                return;
            }


            part.setPartName(
                    nameField.getText());

            part.setBrand(
                    brandField.getText());

            part.setPrice(price);

            part.setQuantity(quantity);

            part.setCategory(
                    categoryField.getText());


            inventoryTable.refresh();


            FileManager.saveInventoryFile(
                    inventoryTable.getItems());


            AuditLogger.log(
                    "Part Updated : "
                            + part.getPartCode());


            showTotalParts();
            showTotalInventoryValue();
            updateLowStockCount();


        } catch (NumberFormatException e) {

            showMessage(
                    "Invalid Input",
                    "Price and quantity must be numbers.");
        }
    }


    // =========================================================
    // DELETE PART
    // =========================================================

    @FXML
    public void deletePart() {

        Part selectedPart =
                inventoryTable
                        .getSelectionModel()
                        .getSelectedItem();


        if (selectedPart == null) {

            showMessage(
                    "Delete Part",
                    "Please select a part first.");

            return;
        }


        Alert alert =
                new Alert(
                        Alert.AlertType.CONFIRMATION);

        alert.setTitle("Delete Part");

        alert.setHeaderText(
                "Delete Selected Part?");

        alert.setContentText(
                "Part Code: "
                        + selectedPart.getPartCode());


        if (alert.showAndWait().orElse(
                ButtonType.CANCEL)
                == ButtonType.OK) {

            inventoryTable
                    .getItems()
                    .remove(selectedPart);


            FileManager.saveInventoryFile(
                    inventoryTable.getItems());


            AuditLogger.log(
                    "Part Deleted : "
                            + selectedPart.getPartCode());


            codeField.clear();
            nameField.clear();
            brandField.clear();
            priceField.clear();
            quantityField.clear();
            categoryField.clear();


            showTotalParts();
            showTotalInventoryValue();
            updateLowStockCount();
        }
    }


    // =========================================================
    // NORMAL SEARCH
    // =========================================================

    @FXML
    public void searchPart() {

        String search =
                searchField.getText()
                        .trim()
                        .toLowerCase();


        inventoryTable.getItems().clear();


        for (Part part :
                FileManager.readInventoryFile()) {

            if (part.getPartCode()
                    .toLowerCase()
                    .contains(search)
                    ||
                    part.getPartName()
                            .toLowerCase()
                            .contains(search)
                    ||
                    part.getBrand()
                            .toLowerCase()
                            .contains(search)
                    ||
                    part.getCategory()
                            .toLowerCase()
                            .contains(search)) {

                inventoryTable
                        .getItems()
                        .add(part);
            }
        }


        AuditLogger.log(
                "Inventory Search");
    }


    // =========================================================
    // SORT PARTS
    // =========================================================

    @FXML
    public void sortParts() {

        javafx.collections.ObservableList<Part> list =
                inventoryTable.getItems();


        for (int i = 0;
             i < list.size() - 1;
             i++) {

            for (int j = 0;
                 j < list.size() - i - 1;
                 j++) {

                if (list.get(j)
                        .getPartCode()
                        .compareToIgnoreCase(
                                list.get(j + 1)
                                        .getPartCode()) > 0) {

                    Part temp = list.get(j);

                    list.set(
                            j,
                            list.get(j + 1));

                    list.set(
                            j + 1,
                            temp);
                }
            }
        }


        inventoryTable.refresh();

        AuditLogger.log(
                "Inventory Sorted");
    }


    // =========================================================
    // LOW STOCK
    // =========================================================

    @FXML
    public void showLowStock() {

        inventoryTable.getItems().clear();

        ArrayList<Part> parts =
                FileManager.readInventoryFile();


        for (Part part : parts) {

            if (part.getQuantity()
                    < LOW_STOCK_THRESHOLD) {

                inventoryTable
                        .getItems()
                        .add(part);
            }
        }


        updateLowStockCount();
    }


    // =========================================================
    // UPDATE LOW STOCK COUNT
    // =========================================================

    public void updateLowStockCount() {

        int count = 0;


        ArrayList<Part> parts =
                FileManager.readInventoryFile();


        for (Part part : parts) {

            if (part.getQuantity()
                    < LOW_STOCK_THRESHOLD) {

                count++;
            }
        }


        lowStockLabel.setText(
                "Low Stock Items : " + count);
    }


    // =========================================================
    // LOAD DEALERS
    // =========================================================

    @FXML
    public void loadDealers() {

        dealerTable.getItems().clear();

        dealerTable.getItems().addAll(
                FileManager.readDealerFile());


        AuditLogger.log(
                "Dealer list loaded");
    }


    // =========================================================
    // RANDOM 4 DEALERS
    // =========================================================

    @FXML
    public void randomDealer() {

        dealerTable.getItems().clear();


        ArrayList<Dealer> dealers =
                FileManager.readDealerFile();


        java.util.Collections.shuffle(
                dealers);


        if (dealers.size() >= 4) {

            dealerTable.getItems().addAll(
                    dealers.subList(0, 4));

        } else {

            dealerTable.getItems().addAll(
                    dealers);
        }


        AuditLogger.log(
                "Random 4 Dealers Selected");
    }


    // =========================================================
    // ADD DEALER
    // =========================================================

    @FXML
    public void addDealer() {

        Dealer dealer =
                new Dealer(
                        dealerIdField.getText(),
                        dealerNameField.getText(),
                        phoneField.getText(),
                        locationField.getText());


        dealerTable.getItems().add(dealer);


        FileManager.saveDealerFile(
                dealerTable.getItems());


        AuditLogger.log(
                "Dealer Added");


        dealerIdField.clear();
        dealerNameField.clear();
        phoneField.clear();
        locationField.clear();
    }


    // =========================================================
    // UPDATE DEALER
    // =========================================================

    @FXML
    public void updateDealer() {

        Dealer dealer =
                dealerTable
                        .getSelectionModel()
                        .getSelectedItem();


        if (dealer == null) {

            showMessage(
                    "Update Dealer",
                    "Please select a dealer first.");

            return;
        }


        dealer.setDealerName(
                dealerNameField.getText());

        dealer.setPhoneNumber(
                phoneField.getText());

        dealer.setLocation(
                locationField.getText());


        dealerTable.refresh();


        FileManager.saveDealerFile(
                dealerTable.getItems());


        AuditLogger.log(
                "Dealer Updated");
    }


    // =========================================================
    // DELETE DEALER
    // =========================================================

    @FXML
    public void deleteDealer() {

        Dealer dealer =
                dealerTable
                        .getSelectionModel()
                        .getSelectedItem();


        if (dealer != null) {

            dealerTable
                    .getItems()
                    .remove(dealer);


            FileManager.saveDealerFile(
                    dealerTable.getItems());


            AuditLogger.log(
                    "Dealer Deleted");
        }
    }


    // =========================================================
    // SELECT DEALER
    // =========================================================

    @FXML
    public void selectDealer() {

        Dealer dealer =
                dealerTable
                        .getSelectionModel()
                        .getSelectedItem();


        if (dealer == null) {
            return;
        }


        dealerIdField.setText(
                dealer.getDealerId());

        dealerNameField.setText(
                dealer.getDealerName());

        phoneField.setText(
                dealer.getPhone());

        locationField.setText(
                dealer.getLocation());
    }


    // =========================================================
    // SEARCH DEALER
    // =========================================================

    @FXML
    public void searchDealer() {

        String search =
                dealerSearchField.getText()
                        .trim()
                        .toLowerCase();


        dealerTable.getItems().clear();


        for (Dealer dealer :
                FileManager.readDealerFile()) {

            if (dealer.getDealerId()
                    .toLowerCase()
                    .contains(search)
                    ||
                    dealer.getDealerName()
                            .toLowerCase()
                            .contains(search)
                    ||
                    dealer.getPhone()
                            .toLowerCase()
                            .contains(search)
                    ||
                    dealer.getLocation()
                            .toLowerCase()
                            .contains(search)) {

                dealerTable
                        .getItems()
                        .add(dealer);
            }
        }


        AuditLogger.log(
                "Dealer Search");
    }


    // =========================================================
    // TOTAL PARTS
    // =========================================================

    @FXML
    public void showTotalParts() {

        int total =
                FileManager
                        .readInventoryFile()
                        .size();


        totalPartsLabel.setText(
                "Total Parts : " + total);
    }


    // =========================================================
    // TOTAL INVENTORY VALUE
    // =========================================================

    @FXML
    public void showTotalInventoryValue() {

        double totalValue = 0;


        for (Part part :
                FileManager.readInventoryFile()) {

            totalValue =
                    totalValue
                            + (part.getPrice()
                            * part.getQuantity());
        }


        totalValueLabel.setText(
                String.format(
                        "Total Inventory Value : Rs. %.2f",
                        totalValue));
    }


    // =========================================================
    // MULTI CRITERIA SEARCH
    // =========================================================

    @FXML
    public void multiCriteriaSearch() {

        String name =
                searchName.getText()
                        .trim()
                        .toLowerCase();

        String category =
                searchCategory.getText()
                        .trim()
                        .toLowerCase();

        String priceText =
                searchPrice.getText()
                        .trim();


        double maxPrice =
                Double.MAX_VALUE;


        if (!priceText.isEmpty()) {

            try {

                maxPrice =
                        Double.parseDouble(
                                priceText);

            } catch (NumberFormatException e) {

                showMessage(
                        "Invalid Price",
                        "Please enter a valid price.");

                return;
            }
        }


        inventoryTable.getItems().clear();


        for (Part part :
                FileManager.readInventoryFile()) {

            boolean nameMatch =
                    name.isEmpty()
                            ||
                            part.getPartName()
                                    .toLowerCase()
                                    .contains(name);


            boolean categoryMatch =
                    category.isEmpty()
                            ||
                            part.getCategory()
                                    .toLowerCase()
                                    .contains(category);


            boolean priceMatch =
                    part.getPrice()
                            <= maxPrice;


            if (nameMatch
                    && categoryMatch
                    && priceMatch) {

                inventoryTable
                        .getItems()
                        .add(part);
            }
        }


        AuditLogger.log(
                "Multi Criteria Search");
    }


    // =========================================================
    // ADD TO CART
    // =========================================================

    @FXML
    public void addToCart() {

        Part selectedPart =
                inventoryTable
                        .getSelectionModel()
                        .getSelectedItem();


        if (selectedPart == null) {

            showMessage(
                    "Cart",
                    "Please select a part first.");

            return;
        }


        int quantity;


        try {

            quantity =
                    Integer.parseInt(
                            cartQuantityField
                                    .getText()
                                    .trim());

        } catch (NumberFormatException e) {

            showMessage(
                    "Invalid Quantity",
                    "Enter a valid quantity.");

            return;
        }


        if (quantity <= 0) {

            showMessage(
                    "Invalid Quantity",
                    "Quantity must be greater than zero.");

            return;
        }


        if (quantity >
                selectedPart.getQuantity()) {

            showMessage(
                    "Stock Error",
                    "Not enough stock available.");

            return;
        }


        // Add item

        shoppingCart.addItem(
                selectedPart,
                quantity);


        // Refresh cart table

        cartTable.getItems().clear();

        cartTable.getItems().addAll(
                shoppingCart.getItems());


        // IMPORTANT:
        // Automatically calculate
        // bulk + synergy discount

        updateCartTotal();


        AuditLogger.log(
                "Part Added To Cart : "
                        + selectedPart.getPartCode()
                        + " Quantity: "
                        + quantity);
    }


    // =========================================================
    // AUTOMATIC CART TOTAL
    // =========================================================

    @FXML
    public void updateCartTotal() {

        double originalTotal =
                shoppingCart.getTotal();

        double discountedTotal =
                shoppingCart.calculateCheckoutTotal();


        if (discountedTotal < originalTotal) {

            cartTotalLabel.setText(
                    String.format(
                            "Total: Rs. %.2f  (Discount Applied)",
                            discountedTotal));

        } else {

            cartTotalLabel.setText(
                    String.format(
                            "Total: Rs. %.2f",
                            discountedTotal));
        }
    }


    // =========================================================
    // CLEAR CART
    // =========================================================

    @FXML
    public void clearCart() {

        shoppingCart.clearCart();

        cartTable.getItems().clear();

        cartTotalLabel.setText(
                "Total: Rs. 0.00");
    }


    // =========================================================
    // MANUAL DISCOUNT
    // =========================================================

    @FXML
    public void applyDiscount() {

        showMessage(
                "Automatic Discount",
                "Discounts are applied automatically.\n\n"
                        + "3 or more units of one item = 5% bulk discount.\n"
                        + "Engine + Electrical = 10% synergy discount.");
    }


    // =========================================================
    // CHECKOUT
    // =========================================================

    @FXML
    public void checkout() {

        if (shoppingCart.getItems().isEmpty()) {

            showMessage(
                    "Checkout",
                    "Cart is empty.");

            return;
        }


        double originalTotal =
                shoppingCart.getTotal();


        double finalTotal =
                shoppingCart.calculateCheckoutTotal();


        cartTotalLabel.setText(
                String.format(
                        "Final Checkout: Rs. %.2f",
                        finalTotal));


        AuditLogger.log(
                "Checkout completed. "
                        + "Original: Rs. "
                        + originalTotal
                        + " Final: Rs. "
                        + finalTotal);


        System.out.println(
                "Original Total: Rs. "
                        + originalTotal);

        System.out.println(
                "Final Checkout: Rs. "
                        + finalTotal);
    }


    // =========================================================
    // MESSAGE BOX
    // =========================================================

    private void showMessage(
            String title,
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION);

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}