
# API Accreditation

API Accreditation -  RESTful HTTP service which 
(i) is leveraged by administrators to keep
track of users’ accreditation status, and 
(ii) serves a client facing web application, serving the
current status of a user’s accreditation status.




##  Execute project

To run 

```bash
  mvn clean package
  docker build -t accreditation_api:latest .
  docker-compose up
```

## To access

http://localhost:8080/swagger-ui/index.html

## Stack

**Back-end:** SpringBoot, Kafka, Java 11, Postgre


## Autores

- Claudemir - claudemirx3@gmail.com

## Git

- 

