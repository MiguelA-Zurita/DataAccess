package org.example;

import jakarta.persistence.Id;

public class ItemSales {
    @Id
    private int idItem;
    @Id
    private int idSales;
    private int quantity;

    public ItemSales(int idItem, int idSales, int quantity){
        this.idItem = idItem;
        this.idSales = idSales;
        this.quantity = quantity;
    }

    public ItemSales(){}

    public int getIdItem() {
        return idItem;
    }
    public int getIdSales() {
        return idSales;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
    public void setIdSales(int idSales) {
        this.idSales = idSales;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
