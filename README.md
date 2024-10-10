# Room Reservation System

This project is a Room Reservation System built using Java and Spring Boot. It allows users to manage room reservations efficiently.

## Features

- **Create Reservation**: Users can create new room reservations.
- **View Reservations**: Users can view all reservations or filter by room and date range.
- **Update Reservation**: Users can update existing reservations.
- **Delete Reservation**: Users can delete reservations.

## Technologies Used

- **Java**: The primary programming language used.
- **Spring Boot**: Framework for building the application.
- **Maven**: Dependency management and build tool.

## Getting Started

### Prerequisites

- Java 22
- Maven
- Spring Boot 3.3.4

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/room-reservation.git
    ```
2. Navigate to the project directory:
    ```sh
    cd room-reservation
    ```
3. Build the project using Maven:
    ```sh
    mvn clean install
    ```

### Running the Application

1. Run the Spring Boot application:
    ```sh
    mvn spring-boot:run
    ```
2. The application will be accessible at `http://localhost:8080`.

## API Endpoints

- `GET /api/reservations`: Retrieve all reservations.
- `GET /api/reservations/{id}`: Retrieve a reservation by ID.
- `POST /api/reservations`: Create a new reservation.
- `PUT /api/reservations/{id}`: Update an existing reservation.
- `DELETE /api/reservations/{id}`: Delete a reservation.
- `GET /api/reservations/{roomName}?start={start}&end={end}`: Retrieve reservations for a specific room within a date range.

## License

This project is licensed under the MIT License.# Room Reservation System

This project is a Room Reservation System built using Java and Spring Boot. It allows users to manage room reservations efficiently.

## Features

- **Create Reservation**: Users can create new room reservations.
- **View Reservations**: Users can view all reservations or filter by room and date range.
- **Update Reservation**: Users can update existing reservations.
- **Delete Reservation**: Users can delete reservations.

## Technologies Used

- **Java**: The primary programming language used.
- **Spring Boot**: Framework for building the application.
- **Maven**: Dependency management and build tool.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/room-reservation.git
    ```
2. Navigate to the project directory:
    ```sh
    cd room-reservation
    ```
3. Build the project using Maven:
    ```sh
    mvn clean install
    ```

### Running the Application

1. Run the Spring Boot application:
    ```sh
    mvn spring-boot:run
    ```
2. The application will be accessible at `http://localhost:8080`.

## API Endpoints

- `GET /api/reservations`: Retrieve all reservations.
- `GET /api/reservations/{id}`: Retrieve a reservation by ID.
- `POST /api/reservations`: Create a new reservation.
- `PUT /api/reservations/{id}`: Update an existing reservation.
- `DELETE /api/reservations/{id}`: Delete a reservation.
- `GET /api/reservations/{roomName}?start={start}&end={end}`: Retrieve reservations for a specific room within a date range.

