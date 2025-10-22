package org.example;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance(); // Create a SAXParserFactory instance

            SAXParser parser = factory.newSAXParser(); // Create a SAXParser from the factory

            gatoHandler handler = new gatoHandler(); // Create an instance of the custom handler

            Scanner sc = new Scanner(System.in); // Create a Scanner to read user input
            System.out.println("Type an XML file path: "); // Prompt the user for the XML file path
            String filePath = sc.nextLine(); // Read the file path from user input
            parser.parse(filePath, handler); // Parse the XML file with the custom handler
            sc.close(); // Close the Scanner
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage()); // Print any exceptions that occur during parsing
        }
    }
}