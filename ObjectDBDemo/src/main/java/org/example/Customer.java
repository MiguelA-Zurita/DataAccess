package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity
public class Customer { // Customer class
    @Id
    private String dni; // DNI as primary key
    private String name;
    private String firstSurname;
    private String secondSurname;

    @OneToMany(mappedBy = "customer", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)  // Relationship between Customer and Sales with cascade delete
    private Set<Sales> sales; // Sales of the customer

    public Customer(String dni, String name, String firstSurname, String secondSurname) {
        this.dni = dni;
        this.name = name;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
    }

    public Customer() {}

    //Getters and Setters

    public String getDni() {
        return dni;
    }
    public String getName() {
        return name;
    }
    public String getFirstSurname() {
        return firstSurname;
    }
    public String getSecondSurname() {
        return secondSurname;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }
    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public Set<Sales> getSales() { return sales; }

    public void setSales(Set<Sales> sales) { this.sales = sales; }

    // toString method
    @Override
    public String toString() {
        return "Customer{" + "dni='" + dni + '\'' + ", name='" + name + '\'' + ", firstSurname='" + firstSurname + '\'' + ", secondSurname='" + secondSurname + '\'' + '}';
    }
}
