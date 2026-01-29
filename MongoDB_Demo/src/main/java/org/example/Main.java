package org.example;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.json.JsonParseException;
import org.json.JSONObject;

public class Main {
    final static String URI = "mongodb://localhost:27017?directConnection=true";
    final static String DATABASE_NAME = "practica_java";
    final static String COLLECTION_NAME = "elements";

    public static void main(String[] args) {
        // Connects; reads; prints; handles exceptions; closes connection
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE_NAME).getCollection(COLLECTION_NAME);
            try (MongoCursor<Document> cursor = collection.find().cursor()) {
                // Iterates documents; prints valid JSON; skips malformed ones
                while (cursor.hasNext()) {
                    printAsXML(cursor.next());
                }
            } catch (Exception e) {
                System.err.println("Cursor error: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }
    /**
     * Converts document to XML string and prints it; handles parsing errors
     */
    public static void printAsXML(Document document){
        try {
            System.out.println(Converter.convertJsonToXml(new JSONObject(document.toJson())));
        } catch (JsonParseException e) {
            System.err.println("Parsing error: " + e.getMessage());
        }
    }
}