# Java Project

This is a simple Java project that demonstrates the structure of a Java application using Maven for dependency management.

## Project Structure

```
java-project
├── src
│   └── Main.java
├── README.md
└── pom.xml
```

## Requirements

- Java Development Kit (JDK) 8 or higher
- Maven 3.6 or higher

## Building the Project

To build the project, navigate to the project directory and run the following command:

```
mvn clean install
```

## Running the Application

After building the project, you can run the application using the following command:

```
mvn exec:java -Dexec.mainClass="Main"
```

## License

This project is licensed under the MIT License.