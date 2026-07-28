# MALABE TUK TUK - Inventory Management System

## Coursework 2 - Java Programming

### BSc (Hons) Artificial Intelligence and Data Science  
### Module: CM1601 Programming Fundamentals

---

## Project Overview

MALABE TUK TUK Inventory Management System is a JavaFX desktop application developed for managing spare parts inventory and Point of Sale (POS) operations for a three-wheeler spare parts depot.

The system manages inventory, dealers, shopping cart transactions, discounts, low-stock monitoring, searching, and audit logging. The application also handles legacy text files containing inconsistent data formats.

---

# Features Implemented

## Inventory Management

- Load inventory data from legacy text files.
- Add new spare parts.
- Update existing spare parts.
- Delete spare parts.
- Display inventory using JavaFX TableView.
- Calculate total inventory value.
- Display total number of parts.
- Low-stock monitoring.

---

## Data File Handling

The system supports legacy data files containing:

- Multiple delimiters:
  - Comma (,)
  - Pipe (|)
  - Semicolon (;)

- Data cleaning:
  - Removes unnecessary spaces.
  - Handles missing fields.
  - Converts price formats.
  - Normalises categories.

---

## Search and Filtering

The application provides multi-criteria searching:

- Search by part name.
- Search by category.
- Search by maximum price.

Users can combine multiple filters to find required parts quickly.

---

## Dealer Management

Dealer features include:

- Load dealer information from files.
- Add dealers.
- Update dealer details.
- Delete dealers.
- Display dealer information.
- Randomly select dealers.

---

## Point of Sale (POS)

Shopping cart functionality includes:

- Add parts to cart.
- Validate purchase quantity.
- Prevent overselling.
- Calculate checkout totals.
- Update stock after purchase.

### Discount Rules

### Bulk Discount

A 5% discount is applied when:
