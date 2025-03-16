# CityCare - Backend in Spring Boot

## 📊 About the Project

  CityCare is a system for monitoring and managing urban incidents, allowing citizens to report problems and track their resolution. The goal is to improve communication between citizens and responsible authorities, ensuring a faster and more effective response to urban issues.

## 🚀 Features

  - User registration and secure authentication via JWT
    
  - Full CRUD management of urban incidents
    
  - Location tracking and logging
    
  - Role-based access control (ADMIN, USER)
    
  - Automatic notification dispatch
    
  - Integration with a PostgreSQL database
    
  - Support for running in a Docker container

## 🛠️ Technologies Used

  - Java 17 as the main programming language
  
  - Spring Boot 3.x for backend development
  
  - Spring Security for JWT authentication
  
  - Hibernate/JPA for data persistence
  
  - PostgreSQL as the relational database

  - Maven for dependency management
  
  - Docker for containerization and deployment

## 🎯 Project Structure

```

citycare-springboot/
├── src/main/java/br/com/fiap/citycare/
│   ├── controller/  # REST Controllers (Incidents, Users, Locations)
│   ├── dto/         # Data Transfer Objects
│   ├── config/      # Security configurations (JWT, SecurityConfig)
│   ├── service/     # Business logic layer
│   ├── repository/  # Persistence layer (Spring Data JPA)
│   └── model/       # Database entities
├── src/main/resources/
│   ├── application.properties  # Database configuration
│   ├── db/migration/           # Flyway scripts for database versioning
├── pom.xml          # Maven dependencies
├── Dockerfile       # Containerization setup
└── README.md        # Documentation

```
## ⚡ How to Run the Project

### Prerequisites
  - JDK 17
  - Maven 3+
  - Docker (Optional, to run PostgreSQL)

### Steps
  1.	Clone the repository:

    git clone https://github.com/your-user/citycare-springboot.git
    cd citycare-springboot
    
  2.	Set up the PostgreSQL database (if not using Docker, edit application.properties):

    docker run --name citycare-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres

  3.	Compile and run the project:

    ./mvnw spring-boot:run



## 🔑 JWT Authentication

The system uses JSON Web Token (JWT) authentication. To access protected endpoints:
  1.	Log in at ```/api/auth/login``` with a registered user and obtain a token.
  2.	Use the token in the request header:
  ```Authorization: Bearer <your-jwt-token>```



## 📌 Main Endpoints

| Method | Endpoint            | Description                        | Authentication Required |
|--------|---------------------|------------------------------------|-------------------------|
| POST   | `/api/auth/login`   | JWT authentication                | ❌ No                   |
| GET    | `/api/ocorrencias`  | Lists all incidents               | ✅ Yes                  |
| POST   | `/api/ocorrencias`  | Creates a new incident            | ✅ Yes                  |
| GET    | `/api/localizacoes` | Lists locations                   | ✅ Yes                  |
| GET    | `/api/usuarios`     | Lists users (ADMIN only)          | ✅ Yes (ADMIN)          |

## 🎓 Academic Context

  This project was developed as part of the Analysis and Systems Development course at FIAP, with the objective of applying concepts studied throughout the discipline. The development covered the following topics:
  
  - Java Spring Boot for API development
  - Maven for dependency management
  - Hibernate for data persistence
  - Docker for containerization and deployment

#

