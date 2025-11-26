package org.example;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Sales {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private double total;
    private String date;
    private String customerDni;

    public Sales(double total, String date, String customerDni) {
        this.total = total;
        this.date = date;
        this.customerDni = customerDni;
    }

    public Sales() {}

    public double getTotal() {
        return total;
    }
    public String getDate() {
        return date;
    }
    public String getCustomerDni() {
        return customerDni;
    }
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
    public void setCustomerDni(String customerDni) {
        this.customerDni = customerDni;
    }
}
