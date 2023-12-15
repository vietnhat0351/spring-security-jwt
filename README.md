## spring-security-jwt

## Features
* User registration and login with JWT authentication
* Password encryption using BCrypt
* Role-based authorization with Spring Security

## Technologies
* Spring Security
* JSON Web Tokens (JWT)
* BCrypt

## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* PostgreSQL database

## Installation
```bash
# Clone the repository
git clone https://github.com/vietnhat0351/spring-security-jwt.git

# Change into the project directory
cd your-project

# Configure
Configure PostgreSQL connection information in the application.properties file

```

---
## Testing Guide

Open Postman

* Register
  * (POST) localhost:8080/api/v1/auth/register
  * Body {
    "firstName": "",
    "lastName": "",
    "email": "",
    "password": "",
    "role": 
  }
  * Expected response: {"token":"token"}
* Login
  * (POST) localhost:8080/api/v1/auth/authenticate
  * Body {
    "email": "",
    "password": ""
  }
  * Expected response: {"token":"token"}
* Authorization

  Add bearer token
    * Only user has role ADMIN can access: (GET) localhost:8080/api/v1/products/all
    * Only user has role USER can access:(GET) localhost:8080/api/v1/products?id=**
