package org.example;

import jakarta.persistence.*;

@Entity @IdClass(ItemSales.class)
public class ItemSales {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("itemId")
    @JoinColumn(name = "item_id", referencedColumnName = "id") // Relationship between ItemSales and Item
    private Item item; // Item sold

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("salesId")
    @JoinColumn(name = "sales_id", referencedColumnName = "id") // Relationship between ItemSales and Sales
    private Sales sales; // Sales in which the item is sold

    private int quantity;

    public ItemSales(int idItem, int idSales, int quantity){
        Item i = new Item(); // Create the item
        i.setId(idItem);
        this.item = i; // Set the item in the item sales
        Sales s = new Sales();// Create the sales
        s.setId(idSales);  // Set the sales in the item sales
        this.sales = s;
        this.quantity = quantity;
    }

    public ItemSales(){}
    // Getters and Setters
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public Sales getSales() { return sales; }
    public void setSales(Sales sales) { this.sales = sales; }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    // toString method
    @Override
    public String toString() {
        return "ItemSales{" +
                "itemId=" + (item.getId()) +
                ", salesId=" + (sales.getId()) +
                ", quantity=" + quantity + '}';
    }
}
