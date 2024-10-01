# Flight Reservation System Backend Application

This project is a RESTful API built using Java Spring Boot framework, aimed at managing flight reservations. It provides both user-facing functionalities, such as booking flights, and administrative functionalities, such as managing flights and users.

## Project Overview
The system allows users to search for flights based on departure and arrival points, view details, and make reservations. Admin users have additional privileges, including flight and user management.

### Flight and Booking Overview:

- **Flight:** Represents a scheduled trip from a departure airport to an arrival airport.
- **Booking:** Represents a reservation for a particular flight made by a user.

These are the core components around which the system revolves.

## Technologies Used
- **Java 17:** Programming language
- **Spring Boot:** Framework for creating RESTful APIs
- **Spring Data JPA:** Data access framework
- **Spring Security:** Security framework
- **Maven:** Dependency management
- **JUnit 5:** Testing framework
- **Mockito:** Mocking framework
- **PostgreSQL:** Database
- **Docker:** Containerization
- **Swagger:** API documentation
- **JWT:** Security mechanism for user authentication
- **GitHub Actions:** CI/CD pipeline

## Database Schema
### User Table

| Field       | Type                | Description       |
|-------------|---------------------|-------------------|
| id          | Long                | Primary Key       |
| name        | String              | User's name       |
| email       | String (Unique)      | User's email      |
| phoneNumber | String              | Contact number    |
| password    | String              | Hashed password   |
| role        | Enum (ADMIN, USER)  | User's role       |

### Flight Table

| Field            | Type                | Description             |
|------------------|---------------------|-------------------------|
| id               | Long                | Primary Key             |
| flightNumber     | String              | Flight identifier       |
| capacity         | Integer             | Available seats         |
| departureAirport | String              | Departure airport code  |
| arrivalAirport   | String              | Arrival airport code    |
| departureDate    | LocalDateTime       | Scheduled departure     |
| arrivalDate      | LocalDateTime       | Scheduled arrival       |
| price            | Decimal (10, 2)     | Flight cost             |

### Booking Table

| Field           | Type                | Description                  |
|-----------------|---------------------|------------------------------|
| id              | Long                | Primary Key                  |
| bookingNumber   | String              | Unique booking identifier    |
| userId          | Long                | References User(id)          |
| flightId        | Long                | References Flight(id)        |

## CI/CD with GitHub Actions

This project uses GitHub Actions for continuous integration and delivery. The workflow can be found in the `.github/workflows/app.yml` file.

### Pipeline Overview

The CI/CD pipeline consists of two jobs:

#### **build:**

- Triggered on pushes or pull requests to the main branch.
- Sets up a PostgreSQL database for testing.
- Builds the project using Maven, caching dependencies.
- Archives the JAR artifact if the build is successful.

#### **test:**

- Runs after the build job is successful.
- Sets up a PostgreSQL database for integration testing.
- Runs unit and integration tests.
- Archives test reports for review.

## Project Setup

### Running with Docker

1. Install Docker and Docker Compose on your machine.
2. In the project root directory, run the following command to start the application:

   ```bash
   docker-compose up
    ```
3. To stop the application, use:

    ```bash
    docker-compose down
    ```
4. The application will be accessible at `http://localhost:8080`.
5. The Swagger documentation will be available at `http://localhost:8080/swagger-ui.html`.

### Running without Docker

1. Install Java 8+ and Maven on your machine.
2. Set up a PostgreSQL database and update the `application.properties` file with the connection details.
3. Run the following command in the project root directory:

    ```bash
    mvn spring-boot:run
    ```
4. The application will be accessible at `http://localhost:8080`.
5. The Swagger documentation will be available at `http://localhost:8080/swagger-ui.html`.

## API Endpoints

### User Operations

- `POST /api/users/register`: Register a new user.
- `POST /api/users/login`: Log in and retrieve a JWT token.
- `POST /api/bookings/create`: Create a new flight booking.
- `GET /api/flights/search`: Search for flights by departure and arrival airports.

### Admin Operations

- `POST /api/flights/addFlights`: Add a new flight.
- `PUT /api/flights/{id}`: Update the details of a flight by its ID.
- `DELETE /api/flights/{id}`: Remove a flight by its ID.
- `GET /api/users/{id}`: Retrieve the details of a user by their ID.
- `GET /api/users`: Retrieve all users.
- `GET /api/bookings`: Retrieve all bookings.

# Sample API Requests

## Flight Search


### Request

- **URL:** `GET /api/flights/search?departure=IST&arrival=AMS&date=2024-07-15`
- **Method:** `GET`

### Response
```json
[
   {
      "id": 1,
      "flightNumber": "TK1985",
      "capacity": 250,
      "departureAirport": "IST",
      "arrivalAirport": "AMS",
      "departureDate": "2024-07-15T10:30:00",
      "arrivalDate": "2024-07-15T13:45:00",
      "price": 750.0
   },
   {
      "id": 2,
      "flightNumber": "TK1986",
      "capacity": 210,
      "departureAirport": "IST",
      "arrivalAirport": "AMS",
      "departureDate": "2024-07-15T18:00:00",
      "arrivalDate": "2024-07-15T21:15:00",
      "price": 820.0
   }
]
```
## Booking Creation

### Request

- **URL:** `POST /api/bookings/create`
- **Method:** `POST`
- **Request Body:** JSON
- **Example:**
   ```json
   {
       "flightNumber": 1,
       "passengerName": "John Doe",
       "passengerEmail": "johndoe@example.com",
       "passengerPhone": "+1234567890"
   }
   ```

### Response

- **Response Body:** JSON
   ```json
   {
       "id": 123,
       "bookingNumber": "BN456",
       "userId": 1,
       "flightId": 1,
       "passengerName": "John Doe",
       "passengerEmail": "johndoe@example.com",
       "passengerPhone": "+1234567890"
   }
   ```
## API Documentation
The API documentation is available at http://localhost:8080/swagger-ui/index.html.


