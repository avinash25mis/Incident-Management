An application for incident Report Management and user Managemenyt

## Technologies used
Java 11
Spring Boot
Spring Data JPA
Spring Security
Swagger UI
Mapstruct
H2 Database
Maven & Maven wrapper (so that any machine can run the application without having maven)

## Running Steps

Take a pull / download the application

Navigate to the folder which contains pom

run the following if you have maven in the System

##  mvn clean install
##  mvn spring-boot:run

use mvnw (maven wrapper file ) if you do not have maven in the System

##    mvnw spring-boot:run

By default applications will run at port 8082 and context path is /app



### Application  url
------------------------------

http://localhost:8082/app
This Url has two buttons which will take you to swagger and H2 Db page also
or you can navigate to them directly using below urls

### H2 Database in-memory
---------------------------

http://localhost:8082/app/h2-database/login.jsp

### H2 database JDBC URL
-------------------------
jdbc:h2:mem:app


### Swagger URl
-------------------------
http://localhost:8082/app/swagger-ui.html#/




# APIS

1. To authenticate 

POST

localhost:8082/app/authenticate

{
    "username":"admin",
    "password":"admin"
}

 curl -H "Content-Type: application/json" -X POST -d '{"username":"admin","password": "admin"}' http://localhost:8082/app/authenticate
 
 
 2 get All Incident Report








