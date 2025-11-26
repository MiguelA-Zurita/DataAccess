package org.example;

import jakarta.persistence.Id;

public class Customer {
    @Id
    private String dni;
    private String name;
    private String firstSurname;
    private String secondSurname;

    public Customer(String dni, String name, String firstSurname, String secondSurname) {
        this.dni = dni;
        this.name = name;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
    }

    public Customer() {}

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

    @Override
    public String toString() {
        return "Customer{" + "dni='" + dni + '\'' + ", name='" + name + '\'' + ", firstSurname='" + firstSurname + '\'' + ", secondSurname='" + secondSurname + '\'' + '}';
    }
}
