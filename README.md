# Spring Boot Microservices Job Application 

## Overview
job-finder repository is now a Job Application System which involves managing Jobs, companies and company reviews. 
Each microservice is exposed as a REST API, with endpoints for managing job services, company services, and review services. 
These services are registered with a Discovery Server (Eureka), and the API Gateway routes client requests to the appropriate microservices.
I have used different databases for 3 microservices(company, job and review) where company-service uses PostgreSQL, job-service uses MySQL and review-service uses MongoDB (NoSQL)
And the Applicant and application APIs will be uploaded soon..

## Prerequisites

- **Java 17** or higher
- **Maven** 
- **PostgreSQL**
- **MySQL**
- **MongoDB**
- **Eureka**
- **Reactive Gateway**

## Architecture
The project follows a **microservices** architecture, with each service(company-service, job-service, review-service) exposed as an independent REST API. 
The microservices include:

 - **Job Service**: Manages jobs such as creating new jobs for each companies according to the requirement, updating when required,
                       Deleting after the requirement, jobs will be retrieved by Id and all the jobs of a particular company will also be retrievable by company id
 - **Comapny Service**: Handles Company API services such as registering new company, updating, deleting and retrieving company data
 - **Company Review Service**: Shows reviews of each company by its Id, allows to create new reviews for each companies, can be updated it later and deletes the review if not required
 - **Servie Discovery(Eureka)**: Central service registry for all services to register and discover each other.
 - **API Gateway**: Routes requests to the appropriate microservice.

## Future Enhancement
 - **Applicant Service**
 - **Job Application Service**
 - **Job Listing with Pagination**
 - **Application Tracking Status Service**
 - **Docker Containerization**
 - **API Documentation**
   
