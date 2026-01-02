
# Car Management & Fuel Tracking System

A Java-based backend system with a command-line client, developed as part of the CodeHills Academy 10 Technical Assignment.
The project demonstrates REST API design, in-memory data handling, Servlet lifecycle knowledge, and client–server interaction via HTTP.

Project Overview

The system consists of two components:

1. Backend Server

Java & Spring Boot

In-memory storage (Maps / Lists)

RESTful API for car and fuel management

Manual Java Servlet integration

2. CLI Client

Standalone Java application

Uses java.net.http.HttpClient

Communicates with the backend via HTTP

Executed from the terminal

Features

Register cars

Add fuel entries

Calculate fuel statistics

REST API and Servlet sharing the same service logic

No database or authentication (as required)

# Technology Stack:

Java 17+

Spring Boot

Java Servlets

Maven

Jackson (JSON processing)

How to Run the Project
Prerequisites

Java 17 or higher

Maven installed

Verify:

java -version
mvn -version

1. Start the Backend Server

From the project root directory:

mvn spring-boot:run


The server starts on:

http://localhost:8081


If Maven is not installed, the Maven Wrapper can be used:

.\mvnw.cmd spring-boot:run

2. Build the CLI Executable

In a new terminal (project root):

mvn clean package


This creates:

target/car-magement-fuel-tracking-0.0.1-SNAPSHOT.jar

3. Run the CLI Client

⚠️ The backend must be running before executing CLI commands.

Create a Car
java -jar target/car-magement-fuel-tracking-0.0.1-SNAPSHOT.jar create-car --brand Toyota --model Corolla --year 2018

Add Fuel Entry
java -jar target/car-magement-fuel-tracking-0.0.1-SNAPSHOT.jar add-fuel --carId 1 --liters 40 --price 52.5 --odometer 45000

View Fuel Statistics
java -jar target/car-magement-fuel-tracking-0.0.1-SNAPSHOT.jar fuel-stats --carId 1

Example Output
Total fuel: 120 L
Total cost: 155.00
Average consumption: 6.4 L/100km

REST API Endpoints
Action	Method	Endpoint
Create Car	POST	/api/cars
List Cars	GET	/api/cars
Add Fuel	POST	/api/cars/{id}/fuel
Fuel Stats	GET	/api/cars/{id}/fuel/stats
Servlet Integration (Mandatory)
Endpoint
GET /servlet/fuel-stats?carId={id}

Implementation Highlights

Extends HttpServlet

Overrides doGet

Manually parses query parameters

Explicitly sets HTTP status codes and content type

Reuses the same service layer as REST endpoints

This demonstrates understanding of the Servlet request lifecycle and low-level HTTP handling.

Data Storage

All data is stored in memory

No database or persistence layer

Data resets when the application restarts

Error Handling

404 for non-existent car IDs

400 for invalid input

Clear error messages returned to CLI

.gitignore

Sensitive and build-related files are excluded:

/target
/.idea
*.iml
.env
application.properties
application.yml

Repository

GitHub:
https://github.com/nsanzimanarichard1/car-magement-fuel-tracking

Author

Richard Nsanzimana
