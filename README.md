This repository provides an API with a simple CRUD to manage users "transactions". (CREATE, READ, UPDATE and DELETE).

This is inspired by solving a technical challenge and can be use as reference to build a basic CRUD with springboot and java.

# Functional Requirements

## Main Functionality
- Create an application that allows registering transactions to an account.
- The application must allow:
  - Creating new transactions, which must contain the following fields:
    - Transaction ID (int)
    - Transaction amount in pesos (int)
    - Transaction business or commerce (varchar)
    - Tenpista's name (varchar)
    - Transaction date (datetime)
  - Editing a transaction
  - Deleting a transaction

## Restrictions
- Each client can have a maximum of **100 transactions**.
- Transactions **cannot have negative amounts**.
- The transaction date **cannot be later than the current date and time**.


# Technical Requirements

## Backend
- It uses java 21 as backend compiled language.
- **Spring Boot:**
  - Implement a REST API to handle the described functionality.

## Database
- **PostgreSQL:**
  - Use PostgreSQL as the relational database.
  - Design an appropriate structure for storing transactions.

## Rate Limiting
- Implements a limit of **3 requests per minute per client** to prevent system abuse.

## Unit Testing
- Include unit tests for services, repositories, and controllers.
- It use **mocks** for testing.

## Error Handling
- Implement a global HTTP error handler to return **structured and clear responses**.
- Example: For a server error, return HTTP status code **500**.

## Documentation
- Document the endpoints using **Swagger/OpenAPI**.
- TODO: Generate a Swagger UI accessible at `/swagger-ui`.
