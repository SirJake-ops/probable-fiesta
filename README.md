# Task Management API

This is a Task Management API built with Spring Boot, Java, and PostgreSQL. It allows users to manage tasks and user information.

## Prerequisites

- Java 17 or higher
- PostgreSQL 16.3
- Gradle 7.6 or higher

## Getting Started

### Clone the repository

```bash
git clone https://github.com/yourusername/task-management-api.git
cd task-management-api# probable-fiesta
CREATE DATABASE task_management;
```

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/task_management
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
  flyway:
    enabled: true
    locations: classpath:db/migration
    baseline-on-migrate: true
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
  application:
    name: Task Management Application

server:
  port: 8080
```

./gradlew bootRun