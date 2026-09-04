# SmartStock – Inventory Management System

A Spring Boot-based Inventory Management System designed to manage products, categories, customers, users, orders, order items, invoices, and inventory transactions through REST APIs.

SmartStock provides a structured backend for inventory operations, including stock monitoring, low-stock detection, order processing, automated bill calculation, input validation, and transaction management.

## Features

- Product and category management
- Customer and user management
- Order and order item management
- Invoice management
- Inventory transaction management
- Stock monitoring
- Low-stock detection
- Automated bill calculation
- Input validation
- Exception handling
- MySQL database integration
- RESTful API architecture
- Swagger/OpenAPI API documentation

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- REST APIs
- MySQL
- Maven
- Swagger / OpenAPI
- Git & GitHub

## Project Structure

src/
└── main/
    ├── java/
    │   └── com.smartinventory/
    │       ├── controller/
    │       ├── dto/
    │       ├── entity/
    │       ├── enums/
    │       ├── exceptions/
    │       ├── repository/
    │       └── service/
    │
    └── resources/
        └── application.properties

## System Modules

### Product Management

- Add products
- View product details
- Update product information
- Delete products
- Maintain product quantity and pricing
- Associate products with categories

### Category Management

- Create categories
- Retrieve categories
- Update category information
- Delete categories
- Organize products based on categories

### Customer Management

- Maintain customer information
- Associate customers with orders
- Manage customer-related order data

### User Management

- Manage system users
- Maintain user roles
- Associate users with orders and transactions

### Order Management

- Create and manage orders
- Associate orders with customers and users
- Maintain order status
- Calculate order totals

### Order Item Management

- Maintain products included in orders
- Store item quantities and pricing
- Associate order items with their respective orders

### Invoice Management

- Generate and manage invoice information
- Associate invoices with orders
- Maintain invoice-related records

### Inventory Transactions

- Record inventory transactions
- Maintain transaction types
- Track payment status
- Store transaction time and amount

## Stock Monitoring

SmartStock maintains product stock quantities and supports identifying products whose available quantity falls below a defined threshold.

This helps identify low-stock products and supports timely inventory replenishment.

## Automated Bill Calculation

The system calculates order totals based on the products and quantities included in an order.

The calculated amount is associated with the corresponding order and invoice records.

## Input Validation

Request data is validated before processing using Spring validation mechanisms.

Examples include:

- Required field validation
- Blank value validation
- Valid request payload validation

This helps prevent invalid data from entering the system.

## Exception Handling

The application includes custom exception handling for scenarios such as:

- Product not found
- Category not found
- Customer not found
- Order not found
- Invalid requests
- Other application-specific errors

This provides meaningful responses when API operations fail.

## Database

SmartStock uses MySQL as the relational database.

### Main Entities

Product
Category
Customer
User
Order
OrderItem
Invoice
InventoryTransaction

Entity relationships are managed using JPA/Hibernate.

## Architecture

The application follows a layered backend architecture:

Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
MySQL Database

### Controller Layer

Handles HTTP requests and exposes REST endpoints.

### DTO Layer

Defines request and response objects used for API communication.

### Service Layer

Contains business logic and application operations.

### Repository Layer

Provides database access using Spring Data JPA.

### Entity Layer

Contains JPA entities representing database tables.

### Exception Layer

Handles application-specific exceptions and error responses.

### Enum Layer

Contains predefined application values such as:

- User roles
- Order status
- Payment status
- Transaction types

## API Documentation

SmartStock uses Swagger/OpenAPI for API documentation and testing.

After starting the application, Swagger UI can be accessed at:

http://localhost:8080/swagger-ui/index.html

## Getting Started

### Prerequisites

Make sure the following are installed:

- Java 17 or later
- Maven
- MySQL
- Git

## Clone the Repository

git clone <your-repository-url>

Navigate into the project:

cd SmartStock

## Database Configuration

Create a MySQL database:

CREATE DATABASE smart_inventory;

Update the database configuration in:

src/main/resources/application.properties

Example:

spring.datasource.url=jdbc:mysql://localhost:3306/smart_inventory
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format_sql=true

Replace the username and password with your MySQL credentials.

## Run the Application

Using Maven:

mvn spring-boot:run

Or build the project first:

mvn clean install

The application will start at:

http://localhost:8080

## Example API Endpoints

### Categories

POST   /categories
GET    /categories
GET    /categories/{categoryId}
PUT    /categories/{categoryId}
DELETE /categories/{categoryId}

### Products

POST   /products
GET    /products
GET    /products/{productId}
PUT    /products/{productId}
DELETE /products/{productId}

### Customers

POST   /customers
GET    /customers
GET    /customers/{customerId}
PUT    /customers/{customerId}
DELETE /customers/{customerId}

### Orders

POST   /orders
GET    /orders
GET    /orders/{orderId}
PUT    /orders/{orderId}
DELETE /orders/{orderId}

Additional endpoints are available for order items, invoices, users, and inventory transactions.

## Example Request

### Create Category

POST /categories
Content-Type: application/json

Request body:

{
    "categoryName": "Electronics",
    "description": "Electronic products"
}

## Development Tools

- IntelliJ IDEA / VS Code
- Postman
- MySQL
- Maven
- Git
- GitHub
- Swagger UI

## Future Enhancements

- Authentication and authorization using Spring Security
- JWT-based authentication
- Advanced inventory analytics
- Pagination and sorting
- Search and filtering
- Automated email notifications for low stock
- Dashboard for inventory statistics
- Role-based access control
- Docker deployment

## Author

Hemanth Sembilli

B.Tech Computer Science and Engineering

## License

This project is developed for educational and portfolio purposes.
