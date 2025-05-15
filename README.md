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
- It has a Swagger UI accessible at `/swagger-ui`.

# How to Run This Project

This guide will walk you through setting up and running the backend application on your local machine.

## 1. Prerequisites

Ensure you have the following software installed and configured on your system:

*   **Git:** For cloning the repository.
    *   Verify your installation: `git --version`
    *   Download from Git SCM.
*   **Java Development Kit (JDK) 21:**
    *   Verify your installation: `java -version`
    *   Download from Oracle JDK 21 or use a version manager like SDKMAN!.
*   **Apache Maven:**
    *   Verify your installation: `mvn -version`
    *   Download from Apache Maven Project.
*   **PostgreSQL Database (Optional, if using PostgreSQL profile):**
    *   Ensure you have a running PostgreSQL instance.
    *   Download from PostgreSQL Downloads.

## 2. Database Setup

This application can be configured to run with either PostgreSQL (default) or an H2 in-memory database (useful for quick development and testing). We use Spring Profiles to manage these configurations.

### Option A: Using PostgreSQL (Default Profile)

If you want to use PostgreSQL, ensure it's installed and follow these steps:

1.  **Create a Database:**
    Connect to your PostgreSQL server (e.g., using `psql` or a GUI tool like pgAdmin) and create a new database. For example:
    ```sql
    CREATE DATABASE tenpo_transactions;
    ```

2.  **Configure Connection Properties:**
    The default database configuration is in `src/main/resources/application.properties`.
    Update the following properties with your PostgreSQL connection details if they differ from the defaults:

    ```properties
    # PostgreSQL Datasource Configuration
    spring.datasource.url=jdbc:postgresql://localhost:5432/tenpo_transactions
    spring.datasource.username=your_postgres_username
    spring.datasource.password=your_postgres_password

    # JPA/Hibernate Configuration
    # 'update': Hibernate will attempt to update the schema if it differs.
    # 'validate': Hibernate will validate the schema, but not make changes.
    # 'create': Hibernate will drop and re-create the schema on startup (useful for testing, data loss).
    # 'create-drop': Same as 'create', but also drops the schema on application shutdown.
    # 'none': No DDL operations.
    spring.jpa.hibernate.ddl-auto=update
    ```
    **Important:** Replace `your_postgres_username` and `your_postgres_password` with your actual PostgreSQL credentials. The `localhost:5432` and `tenpo_transactions` parts should match your database server address, port, and the database name you created.

## 3. Build the Application

Navigate to the project's root directory (`backend`) in your terminal and use Maven to build the project.

1.  **Clone the repository (if you haven't already):**
    ```bash
    git clone <repository-url> # e.g., git clone https://github.com/your-username/technical-challenge-tenpo.git
    cd technical-challenge-tenpo/backend
    ```
    If you've already cloned and are in the `technical-challenge-tenpo` directory, just `cd backend`.

2.  **Clean and Package:**
    This command will compile the code, run tests, and package the application into a JAR file.
    ```bash
    mvn clean install
    ```
    A successful build will create a JAR file in the `target/` directory (e.g., `target/backend-0.0.1-SNAPSHOT.jar`).

## 4. Run the Application

You can run the Spring Boot application using Maven or by directly executing the JAR file.

**Option 1: Using Maven Spring Boot Plugin (Recommended for development)**
```bash
mvn spring-boot:run
```

**Option 2: Running the executable JAR**
```bash
java -jar target/backend-0.0.1-SNAPSHOT.jar
```
*   **Note:** Replace `backend-0.0.1-SNAPSHOT.jar` with the actual name of the JAR file if it differs. It's typically `<artifactId>-<version>.jar` (e.g., if your artifactId in `pom.xml` is `backend`).

## 5. Accessing the Application

Once the application starts successfully, you should see log output indicating that the Spring application has started. By default, it will be accessible at:

*   **API Base URL:** `http://localhost:8080`
*   **Swagger UI:** `http://localhost:8080/swagger-ui` or `http://localhost:8080/swagger-ui.html`

Check the console output for the exact port if it's configured differently.

## Troubleshooting
*   **Port Conflicts:** If port `8080` is already in use, you can change it in `src/main/resources/application.properties` by adding `server.port=NEW_PORT_NUMBER`.
*   **Database Connection Issues:** Double-check your database credentials, host, port, and database name in `application.properties`. Ensure your PostgreSQL server is running and accessible.
*   **Build Failures:** Review the Maven build logs for specific error messages. Common issues include missing dependencies or compilation errors.
