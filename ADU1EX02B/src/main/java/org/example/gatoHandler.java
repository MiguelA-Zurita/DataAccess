package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileWriter;
import java.io.IOException;

public class gatoHandler extends DefaultHandler{ // Extends DefaultHandler to create a custom SAX handler
    
    StringBuilder value; //values from xml nodes 
    StringBuilder nodeCat; //to build each cat info
    int age; //to store age attribute to create files by age

    public gatoHandler(){  //constructor
        this.value = new StringBuilder();
        this.nodeCat = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String tagName, Attributes attributes) throws IndexOutOfBoundsException { // When starting an element
        this.value.setLength(0); // Clear the value StringBuilder
        if(tagName.equals("gatito")){ // If the element is "gatito", get the "edad" attribute
            this.nodeCat.setLength(0); // Clear the nodeCat StringBuilder since it's a new cat
            this.nodeCat.append("Edad: ").append(attributes.getValue("edad")).append("\n"); // Append age info to nodeCat
            this.age = Integer.parseInt(attributes.getValue("edad")); // Store age for file creation
        }
    }
    @Override
    public void characters(char[] ch, int start, int length)
            throws IndexOutOfBoundsException {
        this.value.append(ch, start, length); // Accumulate characters for the current element
    }

    @Override
    public void endElement(String uri, String localName, String tagName) {
        switch (tagName) { // When ending an element, process is the same for each tag.
            case "gatito":
                System.out.print(this.nodeCat.toString()); // Print the cat info to the terminal
                createFileByAge(this.age, "--------------------\n"); // Separator between cats in the file
                createFileByAge(this.age, this.nodeCat.toString()); // Write the cat info to the corresponding age file
                break;
            case "id": // Process "id" element
                this.nodeCat.append("Id: ").append(this.value.toString().trim()).append("\n");
                break; 
            case "nombre": // Process "nombre" element
                this.nodeCat.append("Nombre: ").append(this.value.toString().trim()).append("\n");
                break;
            case "color": // Process "color" element
                this.nodeCat.append("Color: ").append(this.value.toString().trim()).append("\n");
                break;
            case "raza": // Process "raza" element
                this.nodeCat.append("Raza: ").append(this.value.toString().trim()).append("\n");
                break;
        }
    }

    public void createFileByAge(int age, String content){ // Method to create or append to a file based on age
        try(FileWriter writer = new FileWriter(("src/main/resources/gatitos_" + age + ".txt"), true)){ // Open file in append mode, create if it doesn't exist
            writer.write(content); // Write content to the file
        } catch(IOException e){
            System.out.println(e.getMessage()); // Print any exceptions
        }
    }
}
