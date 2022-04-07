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

###  mvn clean install
###  mvn spring-boot:run

use mvnw (maven wrapper file ) if you do not have maven in the System

###    mvnw spring-boot:run

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

Since the application has authentication security we need jwt token to execute almost all api except few
#### Once we get the token we can add the token on  right of the swagger ui "Authorize" button
after token is added we can maintain a session from the swagger UI



# APIs

#### 1. To authenticate 

also Avalilable at swagger UI (http://localhost:8082/app/swagger-ui.html#/)


POST
localhost:8082/app/authenticate

{
    "username":"admin",
    "password":"admin"
}

 curl -H "Content-Type: application/json" -X POST -d '{"username":"admin","password": "admin"}' http://localhost:8082/app/authenticate
 
 --------------------------------------------------------------------
 
 
 #### 2   get All Incident Report
   
   Available at swagger ui URL (http://localhost:8082/app/swagger-ui.html#/)
 
  
   GET Request after addition the token in the header with key "Authorization"
   http://localhost:8082/app/IncidentReport
   
 
  curl -H "Content-Type: application/json" -H "Authorization: eILJsuMiWNqsI_YFMtLWjwMDqEFYoV9IAMqbCge5k"  -X GET  http://localhost:8082/app/IncidentReport
  
  ------------------------------------------------------------------
  
 #### 3 save an incident Report
 
  Available at swagger ui URL (http://localhost:8082/app/swagger-ui.html#/)
  
  POST request after addition the token in the header with key "Authorization"
  http://localhost:8082/app/IncidentReport
  
   {
  
  "assignee": "string",
  "status": "New",
  "title": "string"
  
  }
  
  
  ------------------------------------------------------------------------------
  
 #### 3 update an incident Report
 
  Also Available at swagger ui URL (http://localhost:8082/app/swagger-ui.html#/)
  
  PUT request after addition the token in the header with key "Authorization"
  http://localhost:8082/app/IncidentReport
  
  {
   "id": 50001,
  "assignee": "string",
  "status": "New",
  "title": "string"
  
  }
  
  In update request you can either supply an id or the title to edit existing indident Report
  
  
  ------------------------------------------------------------------------------------
  
  #### 4 get All User
  Also Available at swagger ui URL (http://localhost:8082/app/swagger-ui.html#/)
   Get Request  http://localhost:8082/app/User
   
   
   --------------------------------------------------------------------------------------
  #### 5 save an User
 
  Also Available at swagger ui URL (http://localhost:8082/app/swagger-ui.html#/)
  
  POST request after addition the token in the header with key "Authorization"
  http://localhost:8082/app/User
  
   {
  
  "firstName": "string",
  "lastName": "string",
  "password": "string",
  "username": "string"
  
  }
  
  -----------------------------------------------------------------------------------------
  #### 6 Update an User
 
  Also Available at swagger ui URL (http://localhost:8082/app/swagger-ui.html#/)
  
  PUT request after addition the token in the header with key "Authorization"
  http://localhost:8082/app/User
  
   {
  "username": "string"
  "firstName": "string",
  "lastName": "string",
  "password": "string",
 
  
  }
  
  Updation will be based on username provided in the update request
  
  -------------------------------------------------------------------------------------------
  
   #### 7 Delete an User
    
  Also Available at swagger ui URL (http://localhost:8082/app/swagger-ui.html#/)
  
  DELETE  request after addition the token in the header with key "Authorization"
  
   http://localhost:8082/app/User/{id}
  
  
   #### 8 Delete an Incident Report
    
  Also Available at swagger ui URL (http://localhost:8082/app/swagger-ui.html#/)
  
   DELETE  request after addition the token in the header with key "Authorization"
   
   http://localhost:8082/app/IncidentReport
   
   
   -------------------------------------------------------------------------------------------
   
   
   
   
   
  








