# AutoGeneralTestAPI
This is a test API application to join Auto & General

## For building and running the application you need:

**JDK 1.8**

**Maven 3**

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. 

1. Import the project locally as a Maven project onto your favourite IDE. Run the main method in the com.autogeneral.test.api.AutoGeneralTestAPI.AutoGeneralTestApiApplication class.
2. Alternatively you can use the Spring Boot Maven plugin like so:
	*mvn spring-boot:run*

Since the application is built using Spring Boot, it uses the embedded Tomcat container to run the app and an embedded H2 database for the persistence layer.

## API Documentation

Once the application is up and running, you can access the Swagger Documentation via the below link:

http://localhost:8080/swagger-ui.html

## Automated Build

The automated build for this project is achieved using maven. To build a jar file for this application:

Navigate to the folder with the pom.xml file and run the following command:

*mvn clean*

*mvn package*

## Automated Test Suite

Unit tests and Integration tests are written using JUNIT and are automated to run during the build process via the maven plugin.

## Libraries

Since it is a spring boot application, all libraries come packaged as part of the application jar

## Additional Features

Code Coverage - JaCoCo
