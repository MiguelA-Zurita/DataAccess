
## Introduction
SQLiteDemo is a small Java console application that demonstrates basic CRUD operations using SQLite with plain JDBC. It manages a simple shop domain consisting of:

- Customers
- Items
- Sales
- Items-Sales (a link table to add items into a sale with quantities)

On application start, the database schema is created automatically if it does not exist. A text-based menu lets you create, list, update, and delete records.

## Development environment
Tested with the following setup:

- Operating System: Windows 11
- IDE: IntelliJ IDEA (Ultimate)
- JDK: 17 (project is compiled with Java 17 as configured in the POM)
- Build tool: Maven

## Class Diagram

![Class diagram](https://github.com/MiguelA-Zurita/DataAccess/blob/main/SQLiteDemo/src/main/resources/DiagramaClases_Botiga.png?raw=true)

## How to run

The simplest way is to run the `org.example.Main` class from your IDE. On startup you will see a menu like:

```
----- SQLite Demo CRUD -----
1. Customers
2. Items
3. Sales
4. Items-Sales (link items to a sale)
0. Exit
```

Follow the prompts to manage data. The SQLite database file `store.db` will be created in the working directory.

## Database schema

Tables created on startup (simplified):

- CUSTOMER(DNI PK, Name, FirstSurname, LastSurname)
- ITEMS(ID PK AUTOINCREMENT, UnitPrice, Name, Stock)
- SALES(ID PK AUTOINCREMENT, TotalPrice, SalesDate, DNI_Member FK nullable)
- ITEMS_SALES(ID_Item FK, ID_Sale FK, Quantity, PK(ID_Item, ID_Sale))

Foreign keys are enforced (PRAGMA foreign_keys = ON).

## 🔧 Technologies Used

- Java 17
- SQLite (via JDBC)
- Maven

## 📦 Dependencies

The project uses the Xerial SQLite JDBC driver:

```xml
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.45.2.0</version>
</dependency>
```

## Notes

- The example does not use an ORM; it uses plain JDBC with prepared statements.
- Nullable member DNI for sales is supported; leave it empty in the menu to store NULL.
