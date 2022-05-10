# IMS - an inventory management system

## Description

The main purpose to develop this project is that practice the tools such as SpringBoot, MySQL, Mybatis, Mybatis-Plus, and Redis.

**SpringBoot**

Spring Boot is an open-source Java-based framework used to create a micro Service.

**MySQL**

MySQL is an [open-source](https://en.wikipedia.org/wiki/Open-source_software) [relational database management system](https://en.wikipedia.org/wiki/Relational_database_management_system) (RDBMS).

**Mybatis**

MyBatis is a first-class persistence framework with support for custom SQL, stored procedures, and advanced mappings. MyBatis eliminates almost all of the JDBC code and manual setting of parameters and retrieval of results. MyBatis can use simple XML or Annotations for configuration and map primitives, Map interfaces, and Java POJOs (Plain Old Java Objects) to database records.

**mybatis-plus**

Mybatis plus is an enhanced tool of Mybatis. After using Mybatis plus, we can not only use the unique functions of Mybatis-plus, but also use the native functions of Mybatis normally. 

**Redis**

Redis is an open-source, in-memory data store used by millions of developers as a database, cache, streaming engine, and message broker.



## How to run?

This is an example of how you may give instructions on setting up the project locally. To get a local copy up and running follow these simple example steps.

#### Installation

Install Java 1.8 at https://www.oracle.com/java/technologies/downloads/

Install Maven 3.8.4. at https://maven.apache.org/install.html

#### run

run maven command at project directory`/ims`  
```shell
mvn install
```
run maven command at project directory `/ims`
```bash
mvn spring-boot:run
```

#### code structure

the root is `com.gc.ims`

the main class is `ImsApplication.class`

`application.properties` contains configuration, such as the database connection information.

`sql/sql.sql` contains the DML for the database table and data.

#### platform and IDE

the IDE is `intelliJ IDEA`.

