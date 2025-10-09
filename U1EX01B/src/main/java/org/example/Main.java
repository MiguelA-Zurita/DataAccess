package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File xmlFile = requestFilePath();
            Node root = getRoot(xmlFile);
            NodeList bookList = root.getChildNodes();
            for (int i = 0; i < bookList.getLength(); i++) {
                Node book = bookList.item(i);
                printInfo(book);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static File requestFilePath() {
        System.out.print("Enter a XML path file: ");
        Scanner sc = new Scanner(System.in);
        String xmlPath = sc.nextLine();
        return (new File(xmlPath));
    }

    public static Node getRoot(File xmlFile) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        Node node = null;
        dbFactory.setIgnoringComments(true);
        dbFactory.setIgnoringElementContentWhitespace(true);
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            node = doc.getFirstChild();
        } catch (ParserConfigurationException e) {
            System.out.println("Error in parsing the XML file");
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (SAXException e) {
            System.out.println("SAXException");
        }
        return (node);
    }

    public static void printInfo(Node book) {
        NodeList attributeList = book.getChildNodes();
        String[] attributes = getChildValue(attributeList);
        if (attributes.length > 0) {
            String Info = "Títol: " + attributes[0] + "\n";
            Info += "Autor: " + attributes[1] + "\n";
            Info += "Any: " + attributes[2] + "\n";
            Info += "Color: " + attributes[3] + "\n";
            Info += "Raza: " + attributes[4] + "\n";
            System.out.print(Info);
        }
    }

    public static String[] getChildValue(NodeList childList) {
        String[] attributes = new String[childList.getLength()];
        for (int i = 1; i < childList.getLength(); i++) {
            Node attribute = childList.item(i);
            if (attribute.getNodeType() == Node.ELEMENT_NODE) {
                attributes[i-1] = attribute.getChildNodes().item(0).getNodeValue();
            }
        }
        return attributes;
    }
}