## Introduction
This module reads an input XML file using a JAXB parser, unmarshalling the xml, builds an instance of a root, with  an array of objects, in this case, cats, and prints each object's data to the terminal in a readable format. For each parsed object it also generates a separate XML file placed under the project’s resources directory (one file per age, in this case). Output filenames are derived from object properties to avoid duplicates, and the module can be run from Main by supplying the path to the source XML.

## Development enviroment
This module has been developed in the following enviroment:
Operating System: Windows 11
IDE: IntelliJ IDEAIntelliJ IDEA 2025.2.2 (Ultimate Edition)
JDK Version: 24
Build #IU-252.26199.169, built on September 18, 2025,
Source revision: 7fd6c69de64ef
Runtime version: 21.0.8+1-b1038.71 amd64 (JCEF 122.1.9)
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
Toolkit: sun.awt.windows.WToolkit
Memory: 2048M
Cores: 12

## Execution
How to execute:

1. Execute Main
2. Watch how each node is printed on the terminal.
3. A file is created for each age, in the example xml. The creation route is src\main\resources\

## 🔧 Technologies Used

- **Java 24**
- **Jakarta XML Binding API 3.0.0** - For XML to Java object mapping
- **JAXB Implementation 4.0.6** - XML binding runtime
- **Eclipse MOXy 3.0.0** - JAXB implementation provider
- **Maven** - Build and dependency management

## 📦 Dependencies

```xml
<dependency>
    <groupId>jakarta.xml.bind</groupId>
    <artifactId>jakarta.xml.bind-api</artifactId>
    <version>3.0.0</version>
</dependency>
<dependency>
    <groupId>com.sun.xml.bind</groupId>
    <artifactId>jaxb-impl</artifactId>
    <version>4.0.6</version>
</dependency>
<dependency>
    <groupId>org.eclipse.persistence</groupId>
    <artifactId>org.eclipse.persistence.moxy</artifactId>
    <version>3.0.0</version>
</dependency>
```
