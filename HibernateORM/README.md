# HibernateORM CRUD Example

A simple Java application demonstrating CRUD (Create, Read, Update, Delete) operations using Hibernate ORM with a MySQL database.

## Overview
This project provides a command-line interface (CLI) to manage a collection of Authors and their Books. It uses Hibernate for Object-Relational Mapping (ORM) and MySQL as the relational database.

### Key Features
- **Author Management**: Add, list, update, and delete authors.
- **Book Management**: Add, list, update, and delete books associated with authors.
- **Hibernate Integration**: Automated table generation and session management.
- **Relationship Mapping**: One-to-Many relationship between Authors and Books.

## Requirements
- **Java**: JDK 14 or higher.
- **Maven**: 3.6+ for dependency management and building.
- **MySQL Server**: A running instance of MySQL.

## Setup

### 1. Database Configuration
Before running the application, ensure you have a MySQL database created (e.g., `hibernate_db`).

Update the connection details in `src/main/resources/bbdd_connection.txt`:
```properties
url=jdbc:mysql://localhost:3306/hibernate_db
user=your_username
password=your_password
```

### 2. Hibernate Configuration
The core Hibernate settings are located in `src/main/resources/hibernate.cfg.xml`. By default, `hibernate.hbm2ddl.auto` is set to `update`, which will automatically create or update the database schema based on the entity classes.

## How to Run

### Build the Project
Use Maven to compile the project:
```bash
mvn clean compile
```

### Execute the Application
Run the main class using the Maven Exec plugin:
```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

## Project Structure
```text
HibernateORM/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── Author.java        # Author entity (One-to-Many with Book)
│   │   │   ├── Book.java          # Book entity (Many-to-One with Author)
│   │   │   ├── HibernateUtil.java # SessionFactory utility
│   │   │   └── Main.java          # Application entry point & CLI menu
│   │   └── resources/
│   │       ├── bbdd_connection.txt # External DB credentials
│   │       └── hibernate.cfg.xml  # Hibernate configuration
│   └── test/                      # Unit tests
├── pom.xml                        # Maven configuration
└── README.md                      # Project documentation
```
