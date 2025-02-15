﻿# Task Management API

This is a Task Management API built with Spring Boot, Java, and PostgreSQL. It allows users to manage tasks and user information.

## Prerequisites

- Java 17 or higher
- PostgreSQL 16.3
- Gradle 7.6 or higher
- Docker [Docker Installation Guide](https://docs.docker.com/get-docker/)

## Getting Started

### Clone the repository

```bash
git clone https://github.com/yourusername/task-management-api.git

cd task-management-api

docker-compose up -d

```
**You can change the database configuration in the `application.yml` file.**
**You can also change the docker-compose file to use a different database of your choosing.**

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

```yaml
version: '3.8'

services:
  db:
    image: postgres:latest
    container_name: task_management_db
    environment:
      POSTGRES_DB: task_management
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  app:
    image: openjdk:17-jdk-slim
    container_name: spring_boot_app
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/task_management
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: postgres
    ports:
      - "8080:8080"
    volumes:
      - .:/app
    working_dir: /app
    command: ./gradlew bootRun
    depends_on:
      - db

volumes:
  postgres_data:
```
