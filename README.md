# MALABE TUK TUK - Inventory Management System

## Coursework 2 - Java Programming

### BSc (Hons) Artificial Intelligence and Data Science

### Module: CM1601 Programming Fundamentals

---

## Project Overview

MALABE TUK TUK Inventory Management System is a JavaFX desktop application developed for managing spare parts inventory and Point of Sale (POS) operations for a three-wheeler spare parts depot.

The system is designed to manage spare parts, dealers, shopping cart transactions, discounts, low-stock monitoring, searching, sorting, and audit logging.

The application also works with legacy text files containing different data formats and delimiters.

---

# Features

## Inventory Management

The system provides the following inventory management functions:

- Load inventory data from a legacy text file.
- Add new spare parts.
- Update existing spare parts.
- Delete spare parts.
- Display spare parts using JavaFX TableView.
- Display total number of parts.
- Calculate total inventory value.
- Monitor low-stock items.
- Search inventory items.
- Sort inventory items.

---

## Data File Handling

The application reads legacy text files containing inconsistent formats.

The system supports different delimiters such as:

- Comma `,`
- Pipe `|`
- Semicolon `;`

The application also performs basic data cleaning, including:

- Removing unnecessary spaces.
- Handling missing non-critical fields.
- Handling different currency formats.
- Handling different category capitalisation.

---

## Search and Filtering

The application provides multi-criteria inventory searching.

Users can search using:

- Part name / keyword
- Category
- Maximum price

Multiple search criteria can be combined to narrow down the results.

---

## Inventory Sorting

The inventory can be sorted using manually implemented sorting logic.

The sorting is based on:

1. Category
2. Part code

Bubble Sort is used instead of Java built-in sorting methods.

---

## Low Stock Monitoring

The system monitors stock levels using a configurable low-stock threshold.

Parts with quantities below the threshold can be identified as low-stock items.

The low-stock information is updated when inventory changes.

---

## Dealer Management

The system provides dealer management functions including:

- Load dealer information from a text file.
- Add dealers.
- Update dealer details.
- Delete dealers.
- Search dealers.
- Display dealer information.
- Random dealer selection.

---

# Point of Sale (POS)

The system includes a shopping cart for handling spare part purchases.

Current POS functions include:

- Select an inventory part.
- Add a part to the shopping cart.
- Enter purchase quantity.
- Validate purchase quantity.
- Prevent purchasing more than the available stock.
- Display cart items.
- Calculate cart totals.
- Clear the shopping cart.

---

## Discount Rules

The system includes discount rules for POS transactions.

### Bulk Discount

A 5% discount is applied when a customer purchases:

**3 or more units of the same item.**

### Synergy Discount

A 10% synergy discount is applied when the shopping cart contains:

- At least one Engine part
- At least one Electrical part

The synergy discount is calculated after the applicable item-level bulk discounts.

> Note: POS discount calculation has been implemented, while some final checkout and stock-update functionality is still being completed.

---

# Audit Logging

The application includes an audit logging system for recording important actions.

The audit log is intended to record:

- Date and time
- Action performed
- Affected part code
- Quantity where applicable

Examples of actions include:

- Add item
- Update item
- Delete item
- Checkout

---

# Object-Oriented Programming

The project is developed using Object-Oriented Programming principles.

Main classes include:

- `Part`
- `Dealer`
- `CartItem`
- `ShoppingCart`
- `FileManager`
- `AuditLogger`
- `HelloController`
- `HelloApplication`

The project uses concepts such as:

- Classes and Objects
- Encapsulation
- Constructors
- Getters and Setters
- Methods
- ArrayList
- Object interaction

---

# Technologies Used

- Java
- JavaFX
- FXML
- Maven
- JUnit
- IntelliJ IDEA
- Text File Storage

---

# Project Structure

```text
MALABE TUK TUK
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.malabetuktuk
│   │   │       ├── HelloApplication.java
│   │   │       ├── HelloController.java
│   │   │       ├── Part.java
│   │   │       ├── Dealer.java
│   │   │       ├── CartItem.java
│   │   │       ├── ShoppingCart.java
│   │   │       ├── FileManager.java
│   │   │       └── AuditLogger.java
│   │   │
│   │   └── resources
│   │       ├── com.example.malabetuktuk
│   │       │   └── hello-view.fxml
│   │       │
│   │       └── data
│   │           ├── inventory_legacy.txt
│   │           ├── dealers_legacy.txt
│   │           └── audit_log.txt
│   │
│   └── test
│       └── java
│
├── pom.xml
└── README.md
