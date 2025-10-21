package org.example;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileWriter;
import java.io.IOException;

public class gatoHandler extends DefaultHandler{
    StringBuilder value;
    public gatoHandler(){
        this.value = new StringBuilder();
        System.out.println("Manejador de gatos creado");
    }

    @Override
    public void startElement(String uri, String localName,
                             String tagName, Attributes attributes) throws IndexOutOfBoundsException {
        this.value.setLength(0);
        if(tagName.equals("gatito")){
            System.out.println("Edad:" + attributes.getValue("edad"));
        }
    }
    @Override
    public void characters(char[] ch, int start, int length)
            throws IndexOutOfBoundsException {
        this.value.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String tagName) {
        switch (tagName) {
            case "gatito":
                System.out.println("-------------------");
                break;
            case "id":
                System.out.println("Id: " + this.value.toString());
                break;
            case "nombre":
                System.out.println("Nombre: " + this.value.toString());
                break;
            case "color":
                System.out.println("Color: " + this.value.toString());
                break;
            case "raza":
                System.out.println("Raza: " + this.value.toString());
                break;
        }
    }

    public void createFileByYear(String year, String content){
        try(FileWriter writer = new FileWriter(("src/main/resources/gatitos_" + year + ".txt"), true)){
            writer.write(content);
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
