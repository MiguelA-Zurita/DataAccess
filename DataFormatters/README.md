# DataFormatters

A Java-based command-line utility for converting data between different formats including XML, JSON, and BSON.

## Overview

DataFormatters provides a simple interface to perform the following conversions:
- XML to JSON
- JSON to XML
- JSON to BSON
- BSON to JSON

Converted files are automatically saved to the `src/main/resources/` directory.

## Requirements

- **Java JDK 24** or higher
- **Maven** 3.x

## Setup and Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd DataFormatters
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

### Running the Application

You can run the application using the following Maven command:

```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

Once started, the application will prompt you for input:
1. **XML to JSON**: Enter the path to an XML file and a target name for the JSON file.
2. **JSON to XML**: Enter the path to a JSON file and a target name for the XML file.
3. **JSON to BSON**: Enter the path to a JSON file and a target name for the BSON file.
4. **BSON to JSON**: Enter the path to a BSON file and a target name for the JSON file.

## Project Structure

```text
DataFormatters/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/example/
│   │   │       ├── Main.java      # Entry point and CLI logic
│   │   │       └── Converter.java # Conversion utility logic
│   │   └── resources/             # Input/Output data files
│   └── test/                      # TODO: Unit tests to be implemented
├── pom.xml                        # Maven configuration
└── README.md                      # Project documentation
```
