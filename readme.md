# Book Catalog Project
**Overview**

This project is a Spring Boot application that uses a PostgreSQL database and is dockerized using Docker Compose.

Technologies Used
- Java 21
- Spring Boot 3.4.x with docker compose support
- PostgreSQL 17.x (from dockerhub postgres:latest)
- Docker desktop
- Maven 3.x
- Database
  - Database name: **book_catalog**
- Docker
  - Docker image: postrgres:latest
  - Docker port mapping: 5432:5432

## How to Run
Clone the repository
1. There are 2 profiles defined.
   1. standalone (starts an h2 database in memory, no docker)
   2. dev (starts a dockerized postgres database)
1. Maven build by default runs with standalone profile (see surefire plugin in pom.xml)
