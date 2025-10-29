package org.example;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        root gatos; // Declare variable to hold root object

        try { // Begin exception handling block
            gatos = unmarshal(); // Call unmarshal method to read XML
            for (cat gato : gatos.getCats()) { // Iterate through each cat in the array
                System.out.println(gato.toString()); // Print cat information
                createFileByAge(gato.getAge(), gato.toString());
            }
        } catch (JAXBException e) { // Catch JAXB-related errors
            System.err.println(e.getMessage()); // Print error message
        } catch (FileNotFoundException e) { // Catch file not found errors
            System.err.println(e.getMessage()); // Print error message

        }
    }

    public static root unmarshal() throws JAXBException, FileNotFoundException { // Method to convert XML to objects
        JAXBContext context = JAXBContext.newInstance(root.class); // Create JAXB context for root class
        Unmarshaller unmarshaller = context.createUnmarshaller(); // Create unmarshaller instance
        return (root) unmarshaller.unmarshal(new FileReader("src/main/resources/gatitos.xml")); // Read and convert XML file to root object
    }

    public static void createFileByAge(int age, String content){ // Method to create or append to a file based on age
        try(FileWriter writer = new FileWriter(("src/main/resources/gatitos_" + age + ".txt"), true)){ // Open file in append mode, create if it doesn't exist
            writer.write(content); // Write content to the file
        } catch(IOException e){
            System.out.println(e.getMessage()); // Print any exceptions
        }
    }
}