package org.example;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id", "name", "age", "color", "breed" }) // Define XML element order
@XmlAccessorType(XmlAccessType.FIELD) // Use field access for XML binding

public class cat {
    @XmlElement(name = "id") // Map to <id> XML element
    private int id;
    @XmlElement(name = "edad") // Map to <edad> XML element
    private int age;
    @XmlElement(name = "nombre") // Map to <nombre> XML element
    private String name;
    @XmlElement(name = "color") // Map to <color> XML element
    private String color;
    @XmlElement(name = "raza") // Map to <raza> XML element
    private String breed;

    public cat() { // Default constructor
        this.id = 0; // Initialize attributes to default values
        this.age = 0;
        this.name = "";
        this.color = "";
        this.breed = "";
    }

    public cat(int id, int age, String name, String color, String breed) { // Parameterized constructor
        this.id = id;
        this.age = age;
        this.name = name;
        this.color = color;
        this.breed = breed;
    }

    public int getId() { // Getter for id
        return id;
    }

    public int getAge() { // Getter for age
        return age;
    }

    public String getName() { // Getter for name
        return name;
    }

    public String getColor() { // Getter for color
        return color;
    }

    public String getBreed() { // Getter for breed
        return breed;
    }

    public void setId(int id) { // Setter for id
        this.id = id;
    }

    public void setAge(int age) { // Setter for age
        this.age = age;
    }

    public void setName(String name) { // Setter for name
        this.name = name;
    }

    public void setColor(String color) { // Setter for color
        this.color = color;
    }

    public void setBreed(String breed) { // Setter for breed
        this.breed = breed;
    }

    public String toString() { // Override toString method
        return "id: " + id + "\n" + // Build formatted string with id
                "age: " + age + "\n" +
                "name: " + name + "\n" +
                "color: " + color + "\n" +
                "breed: " + breed + "\n";
    }
}
