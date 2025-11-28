package org.example;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Item {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private String name;
    private Double price;
    private int stock;

    public Item(String name, Double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    public Item(int id, String name, Double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Item() {}

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Double getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + ", stock=" + stock + '}';
    }
}
