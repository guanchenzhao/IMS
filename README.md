# ims - inventory management system

This is an example of how you may give instructions on setting up the project locally. To get a local copy up and running follow these simple example steps.

## Installation

Install Java 1.8 at https://www.oracle.com/java/technologies/downloads/

Install Maven 3.8.4. at https://maven.apache.org/install.html

## run
run maven command at project directory`/ims`  
```
mvn install
```
run maven command at project directory `/ims`
```
mvn spring-boot:run
```

## code structure
the root is `com.gc.ims`

the main class is `ImsApplication.class`

`application.properties` contains configuration, such as database connection information.

`sql/sql.sql` contains the DML for the database table and data.

## platform and IDE

the IDE is `intelliJ IDEA`.