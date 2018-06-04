package com.codecool.web.model;

public class Product {

    int id;
    String productName;
    int unitPrice;
    int unitsInStock;
    String categoryName;
    String supplierName;

    public Product(int id, String productName, int unitPrice, int unitsInStock, String categoryName, String supplierName) {
        this.id = id;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.categoryName = categoryName;
        this.supplierName = supplierName;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSupplierName() {
        return supplierName;
    }
}
