package org.example;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gatitos") // Map to <gatitos> XML root element
@XmlAccessorType(XmlAccessType.FIELD) // Use field access for XML binding
public class root {
    @XmlElement(name = "gatito") // Map to <gatito> XML elements
    public cat[] cats; // Array to hold cat objects

    public root() { // Default constructor
        this.cats = new cat[0];
    }

    public root(cat[] cats) { // Parameterized constructor
        this.cats = cats;
    }

    public cat[] getCats() { // Getter for cats array
        return cats;
    }

    public void setCats(cat[] cats) { // Setter for cats array
        this.cats = cats;
    }

}
