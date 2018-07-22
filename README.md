# Play REST API

## Appendix

### Introduction

This application consists of REST APIs to perform CRUD operations using PlayFramework, Scala and Elasticsearch(v6.x) as database. 

REST APIs are documented using Swagger.
#### Libraries
* Play Framework - 2.4.11
* Scala Library - 2.11.6
* Accord(validation library for Scala) - 0.7.2
* Elastic4s - 6.2.9

### Running

####Prerequisite
* sbt
* Docker
* docker-compose

Once you have above software installed. Download/checkout the project and jump to project directory. Then run below commands to run the application:

```bash
docker-compose up
```
This will start elasticsearch in docker. You can check the status of elasticsearch from your browser by hitting the URL: <http://localhost:9200/>

Once your elasticsearch is up and running. It's time to start the application.

The following at the command prompt will start the application:

```bash
sbt run
```

Play will start up on the HTTP port at <http://localhost:9000/>.   You don't need to deploy or reload anything -- changing any source code while the server is running will automatically recompile and hot-reload the application on the next HTTP request.

####Swagger
* Swagger documentation can be found at <http://localhost:9000/docs/>