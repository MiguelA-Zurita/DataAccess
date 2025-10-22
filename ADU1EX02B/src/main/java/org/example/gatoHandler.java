package org.example;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileWriter;
import java.io.IOException;

public class gatoHandler extends DefaultHandler{
    StringBuilder value;
    int age;
    public gatoHandler(){
        this.value = new StringBuilder();
        System.out.println("Manejador de gatos creado");
    }

    @Override
    public void startElement(String uri, String localName,
                             String tagName, Attributes attributes) throws IndexOutOfBoundsException {
        if(tagName.equals("gatito")){
            this.value.setLength(0);
            this.value.append("Edad: ").append(attributes.getValue("edad")).append("\n");
            this.age = Integer.parseInt(attributes.getValue("edad"));
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
                System.out.print(this.value.toString());
                createFileByAge(this.age, this.value.toString());
                break;
            case "id":
                this.value.append("Id: ").append(this.value).append("\n");
                break;
            case "nombre":
                this.value.append("Nombre: ").append(this.value).append("\n");
                break;
            case "color":
                this.value.append("Color: ").append(this.value).append("\n");
                break;
            case "raza":
                this.value.append("Raza: ").append(this.value).append("\n");
                break;
        }
    }

    public void createFileByAge(int age, String content){
        try(FileWriter writer = new FileWriter(("src/main/resources/gatitos_" + age + ".txt"), true)){
            writer.write(content);
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
