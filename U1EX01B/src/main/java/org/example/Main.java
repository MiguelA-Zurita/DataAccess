package org.example;

import org.w3c.dom.Document; //Importing libraries
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File xmlFile = requestFilePath(); //request path of the xml File
            Node root = getRoot(xmlFile); //get the root of the xml
            if (root == null) { //In case root isn't found
                System.err.println("Root element not found");
                return;
            }
            NodeList objectList = root.getChildNodes(); //Get the list of the xml
            int FileNumber = 1; //File number for later creating xml for each object
            for (int i = 0; i < objectList.getLength(); i++) { // looping for each object inside the object list
                Node object = objectList.item(i); //get an object from list
                if (object.getNodeType() != Node.ELEMENT_NODE) {  //in case the object is whitespace, or different from an element, skip it
                    continue;
                }
                String info = getInfo(object); //Gets the information from the object
                System.out.println(info); //prints the info
                createFile("src/main/resources/gatito_" + FileNumber + ".xml", info); //creates a xml with all the information written on the pathfile entered.
                FileNumber++; //bumps the file index
            }
        } catch (Exception e) { //in case there's any error, show it on console
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static File requestFilePath() { //method to request the file path and return it as a file
        System.out.print("Enter a XML path file: ");
        Scanner sc = new Scanner(System.in); //Scanner to scan the users input
        String xmlPath = sc.nextLine(); //scans the users input
        return (new File(xmlPath)); //returns the file from the pathfile as a file
    }

    public static Node getRoot(File xmlFile) { //method to get to the root of the xml and return it as a node
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); //create a new instance of DocumentBuilder
        Node root = null; //root of the xml
        dbFactory.setIgnoringComments(true); //ignore comment
        dbFactory.setIgnoringElementContentWhitespace(true); //ignore whitespace
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); //get a new document builder to parse the xml
            Document doc = dBuilder.parse(xmlFile); //parse the xml into a document
            root = doc.getFirstChild(); //gets the root from the document
        } catch (ParserConfigurationException e) { //in case theres a parse config error
            System.out.println("Error in parsing the XML file");
        } catch (IOException e) { //in case theres an IOException
            System.out.println("IOException");
        } catch (SAXException e) { //in case theres a parse exception
            System.out.println("SAXException");
        }
        return (root); //return root
    }

    public static String getInfo(Node object) { //gets the info from an object
        NodeList attributeList = object.getChildNodes(); //get the list of children of that object
        String[] attributes = getChildValue(attributeList); //gets the children value from the list
        String Info = ""; //String to store all the info
        if (attributes.length > 0) { //if any attributes exist, add the to the string
            Info = "Id: " + attributes[0] + "\n"; //adding the info
            Info += "Nombre: " + attributes[1] + "\n";
            Info += "Edat: " + attributes[2] + "\n";
            Info += "Color: " + attributes[3] + "\n";
            Info += "Raza: " + attributes[4] + "\n";
        }
        return (Info); //return the info
    }

    public static String[] getChildValue(NodeList childList) { //method to get the values from an children list
        java.util.List<String> attributes = new java.util.ArrayList<>(); //array list to get the attributes
        for (int i = 0; i < childList.getLength(); i++) { //loop to get each attribute from the list
            Node attribute = childList.item(i); //getting the attribute from each child
            if (attribute.getNodeType() == Node.ELEMENT_NODE) { //if it is an element node, get its value
                String value = attribute.getTextContent(); //get the text content
                if (value != null) { //if it's not null, trim it in case there's space
                    value = value.trim(); //trim the value
                }
                attributes.add(value == null ? "" : value); //in case value is null, add an empty string
            }
        }
        return attributes.toArray(new String[0]); //return the arrayList as a StringArray
    }

    public static void createFile(String filePath, String content) { //method to create file with the params
        try (FileWriter writer = new FileWriter(filePath)) { //try to create a file
            writer.write(content); //write the info inside the file
        } catch (IOException e) { //in case there's any exception, print it
            System.err.println("IOException: " + e.getMessage());
        }
    }
}