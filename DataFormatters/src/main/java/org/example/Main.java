package org.example;

import org.bson.*;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.io.BasicOutputBuffer;
import org.json.*;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main {

    final static String SAVE_DIR_PATH = "src/main/resources/";
    public static Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        xmlToJsonSequence();
        jsonToXmlSequence();
        jsonToBsonSequence();
        bsonToJsonSequence();
        SC.close();
    }

    /**
     * Reads XML file; creates corresponding JSON file
     */
    public static void xmlToJsonSequence() {
        System.out.println("Enter the path of the XML file:");
        String xmlPath = SC.nextLine();
        System.out.println("enter a name for the json file");
        String jsonName = SC.nextLine();
        StringBuilder xmlString = new StringBuilder();
        JSONObject convertedXML = Converter.convertXmlToJson(readContent(xmlPath));
        createFile(jsonName, convertedXML.toString());
    }

    /**
     * Converts JSON file to XML file
     */
    public static void jsonToXmlSequence() {
        System.out.println("Enter the path of the JSON file:");
        String jsonPath = SC.nextLine();
        System.out.println("enter a name for the xml file");
        String xmlName = SC.nextLine();
        String convertedJson = "";
        try {
            convertedJson = Converter.convertJsonToXml(new JSONObject(readContent(jsonPath)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        createFile(xmlName, convertedJson);
    }

    /**
     * Reads BSON file; converts to JSON; persists result
     */
    public static void bsonToJsonSequence() {
        System.out.println("Enter the path of the BSON file:");
        String bsonPath = SC.nextLine();
        System.out.println("enter a name for the JSON file");
        String jsonName = SC.nextLine();
        JSONObject convertedBson = new JSONObject();
        // Maps file; decodes BSON; handles exceptions
        try (FileChannel channel = FileChannel.open(Path.of(bsonPath), StandardOpenOption.READ)){
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            buffer.load();
            BsonReader reader = new BsonBinaryReader(buffer);
            DecoderContext decoderContext = DecoderContext.builder().build();
            DocumentCodec codec = new DocumentCodec();
            convertedBson = Converter.convertBsonToJson(codec.decode(reader, decoderContext));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        createFile(jsonName, convertedBson.toString());
    }

    /**
     * Reads JSON file; converts to BSON; persists to disk
     */
    public static void jsonToBsonSequence() {
        System.out.println("Enter the path of the JSON file:");
        String jsonPath = SC.nextLine();
        System.out.println("enter a name for the BSON file");
        String bsonName = SC.nextLine();
        Document convertedJson = new Document();
        try {
            convertedJson = Converter.convertJsonToBson(new JSONObject(readContent(jsonPath)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        createBinaryFile(bsonName, documentToBytes(convertedJson));
    }

    public static void createFile(String fileName, String content) { //method to create a file with the params
        if (fileName.isEmpty()) { //check if the file name is empty
            System.err.println("File name cannot be empty");
            return;
        }
        if (content.isEmpty()) { //check if the content is empty
            System.err.println("File content cannot be empty");
            return;
        }
        try (FileWriter writer = new FileWriter((SAVE_DIR_PATH + fileName))) { //try to create a file
            writer.write(content); //write the info inside the file
        } catch (IOException e) { //in case there's any exception, print it
            System.err.println("IOException: " + e.getMessage());
        }


    }
    public static void createBinaryFile(String fileName, byte[] content) { //method to create a binary file with the params
        if (fileName.isEmpty()) { //check if the file name is empty
            System.err.println("File name cannot be empty");
            return;
        }
        if (content.length == 0) { //check if the content is empty
            System.err.println("File content cannot be empty");
            return;
        }
        try (FileOutputStream writer = new FileOutputStream((SAVE_DIR_PATH + fileName))) { //try to create a file
            writer.write(content); //write the info inside the file
        } catch (IOException e) { //in case there's any exception, print it
            System.err.println("IOException: " + e.getMessage());
        }
    }

    /**
     * Converts document to byte array using BSON
     */
    public static byte[] documentToBytes(org.bson.Document document) {
        BasicOutputBuffer buffer = new BasicOutputBuffer();
        try (BsonBinaryWriter writer = new BsonBinaryWriter(buffer)) {
            new DocumentCodec().encode(writer, document, EncoderContext.builder().isEncodingCollectibleDocument(true).build());
        }
        return buffer.toByteArray();
    }

    public static String readContent(String filePath) {
        StringBuilder content = new StringBuilder();
        // Reads file content into string; handles exceptions
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
        return content.toString();
    }
}