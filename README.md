# Task Management REST API

## Project Overview
A Spring Boot REST API for managing tasks, demonstrating best practices in software development and RESTful design.

## Technologies Used
- Java 17
- Spring Boot 3.4.2
- Spring Web
- Spring Data JPA
- H2 Database
- JUnit 5
- Mockito

## Features
- CRUD operations for tasks
- Persistent file-based H2 database
- Comprehensive validation
- Global exception handling
- Layered architecture (Controller, Service, DAO, Repository)

## Project Structure
```
src
├── main
│   ├── java
│   │   └── com.example.taskmanagement
│   │       ├── controller
│   │       ├── model
│   │       ├── service
│   │       ├── dao
│   │       ├── dto
│   │       ├── repository
│   │       └── exception
│   └── resources
│       ├── application.properties
│       ├── data.sql
│       └── schema.sql
└── test
    └── java
        └── com.example.taskmanagement
            └── (corresponding test classes)
```

## Key Components
- **Model**: `Task` entity with validation constraints
- **DTO**: Data Transfer Objects for API communication
- **Service**: Business logic implementation
- **DAO**: Data access abstraction
- **Repository**: Spring Data JPA repository
- **Controller**: RESTful endpoints

## Endpoints
- `GET /api/tasks`: Retrieve all tasks
- `GET /api/tasks/{id}`: Retrieve specific task
- `POST /api/tasks`: Create a new task
- `PUT /api/tasks/{id}`: Update an existing task
- `DELETE /api/tasks/{id}`: Delete a task

## Testing
- Unit tests for Model, Service, and Controller layers
- Integration tests for DAO and Repository
- Mockito for mocking dependencies
- JUnit 5 for test framework

## Database
- H2 in-memory database
- File-based persistence
- Pre-populated with initial data
- Accessible via H2 console

## Running the Application
1. Clone the repository
2. Run `./mvnw spring-boot:run`
3. Access API at `http://localhost:8080/api/tasks`
4. H2 Console at `http://localhost:8080/h2-console`

## Testing
Run tests using: `./mvnw test`
