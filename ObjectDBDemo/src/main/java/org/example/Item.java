package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY) // Generate ID automatically
    private int id; // Item ID
    private String name;
    private Double price;
    private int stock;

    @OneToMany(mappedBy = "item") // Relationship between Item and ItemSales
    private Set<ItemSales> itemSales; // Items sold in sales

    public Item(String name, Double price, int stock) { // Constructor
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
    // Getters and Setters
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

    public Set<ItemSales> getItemSales() { return itemSales; }
    public void setItemSales(Set<ItemSales> itemSales) { this.itemSales = itemSales; }
    // toString method
    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + ", stock=" + stock + '}';
    }
}
