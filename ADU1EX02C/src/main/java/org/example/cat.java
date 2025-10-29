package org.example;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "gatitos")
@XmlType(propOrder = { "id", "name", "age", "color", "breed" })

public class cat {
    private int id;
    private int age;
    private String name;
    private String color;
    private String breed;

    public cat(int id, int age, String name, String color, String breed) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.color = color;
        this.breed = breed;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getBreed() {
        return breed;
    }

    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "edad")
    public void setAge(int age) {
        this.age = age;
    }
    @XmlElement(name = "nombre")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "color")
    public void setColor(String color) {
        this.color = color;
    }

    @XmlElement(name = "raza")
    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String toString() {
        return "id: " + id + "\n" +
                "age: " + age + "\n" +
                "name: " + name + "\n" +
                "color: " + color + "\n" +
                "breed: " + breed + "\n";
    }
}
