# MongoDB Demo

A Java-based demonstration project for interacting with MongoDB, featuring BSON to JSON/XML conversion utilities.

## Overview
This project demonstrates how to connect to a MongoDB database, retrieve documents, and convert them between various formats (BSON, JSON, and XML). It uses the official MongoDB Java driver and the `org.json` library for format transformations.

## Requirements
- **Java:** Version 24
- **Maven:** 3.x or higher
- **MongoDB:** A running instance (default: `localhost:27017`)

## Project Structure
```text
MongoDB_Demo/
├── pom.xml                  # Maven project configuration
└── src/
    └── main/
        └── java/
            └── org/
                └── example/
                    ├── Main.java       # Application entry point
                    └── Converter.java  # Utility class for XML/JSON/BSON conversion
```

## Setup & Run

### 1. Configure MongoDB
Ensure MongoDB is running locally. By default, the application connects to:
- **URI:** `mongodb://localhost:27017?directConnection=true`
- **Database:** `practica_java`
- **Collection:** `elements`

### 2. Build the project
```bash
mvn clean compile
```

### 3. Run the application
```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

## Environment Variables / Configuration
Currently, configurations are hardcoded in `Main.java`:
- `URI`: MongoDB connection string.
- `DATABASE_NAME`: Target database name.
- `COLLECTION_NAME`: Target collection name.


