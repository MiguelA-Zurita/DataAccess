package org.example;

import jakarta.persistence.*;

@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY) // Generate ID automatically
    private int id; // Sales ID
    private double total;
    private String date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_dni", referencedColumnName = "dni", nullable = true) // Relationship between Sales and Customer
    private Customer customer; // Customer of the sale

    @OneToMany(mappedBy = "sales", cascade = CascadeType.ALL, orphanRemoval = true) // Relationship between Sales and ItemSales with cascade delete
    private java.util.Set<ItemSales> itemSales; // Items sold in the sale

    public Sales(double total, String date, String customerDni) { // Constructor
        this.total = total;
        this.date = date;
        if (customerDni != null) { // If customerDni is not null, create a new Customer object
            Customer c = new Customer();
            c.setDni(customerDni);
            this.customer = c;
        }
    }
    public Sales(int id, double total, String date, String customerDni) {
        this.id = id;
        this.total = total;
        this.date = date;
        if (customerDni != null) {
            Customer c = new Customer();
            c.setDni(customerDni);
            this.customer = c;
        }
    }

    public Sales() {}
    // Getters and Setters
    public double getTotal() {
        return total;
    }
    public String getDate() {
        return date;
    }
    public Customer getCustomer() { return customer; }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public java.util.Set<ItemSales> getItemSales() { return itemSales; }

    public void setItemSales(java.util.Set<ItemSales> itemSales) { this.itemSales = itemSales; }
    // toString method
    @Override
    public String toString() {
        return "Sales{" + "id=" + id + ", total=" + total + ", date='" + date + '\'' + ", customer=" + (customer != null ? customer.getDni() : null) + '}';
    }
}
