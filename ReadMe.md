# Getting Started

### Reference Documentation

this project is configured with maven and using JDK 1.8.

To Build Project:

Execute the following command inside project root folder:

	mvn clean install
To Run Project:
use below command inside project root folder 

	mvn spring-boot:run
default port to execute is 8080 

List of API's

Call Post API with valid request body  
POST /transactions - saves the given transaction if valid for stats summary 


GET /statistics - gets the aggregated summary statistics
GET /transactions - to delete the transactions . 


