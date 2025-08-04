# QuizBackend

QuizBackend is a microservices-based backend system built using Spring Boot. It is designed to manage quizzes, questions, users, and results for educational and trivia applications. The project is structured to support modularity and scalability.

## Features

- Microservice architecture for separation of concerns and ease of scaling.
- CRUD operations for quizzes, questions, users, and results.
- RESTful API endpoints.
- Docker Compose for easy local development and orchestration.

## Project Structure

At the root of the project you’ll find files for build (Gradle), Docker Compose, and standard configuration. The main application code resides in the `src` directory, which may include the following microservices (typically as submodules or packages):

### Microservices Overview

Based on the standard Spring Boot microservices setup, here’s what you can expect (please check the `src` directory for concrete service packages):

- **Quiz Service**  
  Handles quiz definitions, quiz CRUD, and retrieving lists of quizzes. Responsible for the main quiz catalog.

- **Question Service**  
  Manages questions related to quizzes. Supports CRUD operations for question creation, retrieval, updating, and deletion.

- **User Service**  
  Manages user registration, authentication, and user profile data. Handles user-related queries and permissions.

- **Result Service**  
  Records and retrieves quiz participation results, scores, and user performance analytics.

> Each service is typically implemented as a Spring Boot application, communicating over REST APIs. Service discovery, inter-service communication, and configuration management can be supported via Spring Cloud, but please refer to specific source code for details.

### Configuration

- Edit `src/main/resources/application.properties` in each service for database and port settings.
- Environment variables can be set for Docker Compose as needed.

## API Endpoints
- Each microservice exposes its own set of REST endpoints. Refer to the source code in `src` for detailed controllers and endpoints.
---
