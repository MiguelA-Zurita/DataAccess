package org.example;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.bson.json.JsonParseException;

public class Main {
    final static String URI = "localhost:27017";

    public static void main(String[] args) {
        // Connects; reads; prints; handles exceptions; closes connection
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            MongoDatabase database = mongoClient.getDatabase("project_java");
            MongoCollection<Document> collection = database.getCollection("elements");
            try (MongoCursor<Document> cursor = collection.find().cursor()) {
                // Iterates documents; prints valid JSON; skips malformed ones
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    String jsonString = document.toJson();
                    try {
                        JsonObject jsonObject = new JsonObject(jsonString);
                        System.out.println(jsonObject);
                    } catch (JsonParseException e) {
                        System.err.println("Parsing error: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}