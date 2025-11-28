
# ObjectDBDemo

Simple console application demonstrating basic CRUD operations and JPA relationships using ObjectDB. It models a small sales domain with the following entities:

- Customer (identified by `dni`) with 1..* Sales
- Item with 1..* ItemSales
- Sales with *..* Item through the join entity ItemSales (which stores `quantity`)

The app provides a text-based menu to create, update, delete, and list `Customer`, `Item`, `Sales`, and `ItemSales` records.

## Development Environment

- Language: Java (Jakarta Persistence API)
- JDK: 24 (as configured in `pom.xml`). JDK 21+ typically works, but match the pom when possible.
- Build tool: Maven 3.9+
- IDE (recommended): IntelliJ IDEA (Ultimate)
- Database: ObjectDB (embedded file `ObjectDBDemo.odb` in the project root). No external DB server is required for the current configuration.

Project layout:

- `src/main/java/org/example` — source code
- `src/main/resources/persistance.xml` — example persistence units (not used by the current embedded setup)
- `ObjectDBDemo.odb` — ObjectDB database file (created/used at runtime)

## Entity-Relation Diagram

![Class diagram](https://github.com/MiguelA-Zurita/DataAccess/blob/main/ObjectDBDemo/src/main/resources/DiagramaClases_Botiga.png?raw=true)
## How to Run

You can run the app either from an IDE or using Maven from the command line.

### Option A: Run from IntelliJ IDEA
1. Open the project folder in IntelliJ IDEA.
2. Ensure the Project SDK is set to a JDK compatible with the pom (JDK 24 recommended).
3. Build the project.
4. Run the `org.example.Main` class. The console menu will appear.

### Option B: Run with Maven (no changes to pom.xml required)

From the project root:

```
mvn -q -e compile
mvn -q -Dexec.mainClass=org.example.Main -Dexec.classpathScope=runtime exec:java
```

Notes:
- On first run, the file database `ObjectDBDemo.odb` will be created in the project root.
- The application uses an embedded `EntityManagerFactory` via `Persistence.createEntityManagerFactory("ObjectDBDemo.odb")`, so it does not rely on `persistance.xml` by default.
- If you prefer running ObjectDB in server mode (as hinted in `src/main/resources/persistance.xml`), you would need to start the ObjectDB server and adjust the code to use a named persistence unit instead of a file-based factory.

## Technologies Used

- Java + Jakarta Persistence (JPA)
- ObjectDB (embedded)
- Maven

## Dependencies

Managed via Maven (`pom.xml`):

- `com.objectdb:objectdb-jk:2.9.4` — ObjectDB database engine and JPA provider

Transitive/runtime components:

- Jakarta Persistence API (provided via the ObjectDB dependency)

## Domain Model Overview

- `Customer`
  - `@Entity` with primary key `dni`
  - `@OneToMany(mappedBy = "customer") Set<Sales> sales`

- `Item`
- `@Entity` with generated integer `id`
  - `@OneToMany(mappedBy = "item") Set<ItemSales> itemSales`

- `Sales`
  - `@Entity` with generated integer `id`
  - `@ManyToOne @JoinColumn(name = "customer_dni", referencedColumnName = "dni", nullable = true) Customer customer`
  - `@OneToMany(mappedBy = "sales") Set<ItemSales> itemSales`

- `ItemSales` (join entity)
  - `@Entity`
  - Composite key via `@EmbeddedId ItemSalesId`
  - `@ManyToOne @MapsId("itemId") @JoinColumn(name = "item_id") Item item`
  - `@ManyToOne @MapsId("salesId") @JoinColumn(name = "sales_id") Sales sales`
  - Field `quantity`

## CLI Usage Tips

After starting the app, you will see a main menu with options to manage Customers, Items, Sales, and ItemSales. Follow the prompts to insert, update, delete, or list records. IDs for `Item` and `Sales` are generated; `Customer` uses the DNI you provide.

## Troubleshooting

- Build fails due to JDK version: ensure your Java SDK matches or exceeds the version in the pom (24). You can adjust `maven.compiler.source/target` in `pom.xml` to your installed JDK if needed.
- Cannot connect to ObjectDB server: the current code uses an embedded database and does not require the server. If you intentionally switch to server mode, make sure the server is running and the persistence unit is used in the code.
