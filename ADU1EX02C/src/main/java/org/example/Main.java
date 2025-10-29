package org.example;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Main {
    public static void main(String[] args) {

    }

    public cat unmarshal() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(cat.class);
        return (cat) context.createUnmarshaller()
                .unmarshal(new FileReader("src/main/resources/gatitos.xml"));
    }
}