# Mock Drive Rating System 🎯

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13.0-blue.svg)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A comprehensive Spring Boot application for managing mock drive ratings and reviews, enabling users to provide feedback, track performance, and analyze interview preparation effectiveness.

## 📑 Table of Contents
- [System Overview](#-system-overview)
- [Architecture](#-architecture)
- [Features](#-features)
- [API Documentation](#-api-documentation)
- [Database Schema](#-database-schema)
- [Setup Instructions](#-setup-instructions)
- [Contributing](#-contributing)
- [Testing Guide](#-testing-guide)
- [License](#-license)
- [Contact](#-contact)

## 📋 System Overview

The Mock Drive Rating System provides a robust platform for:
- Creating and managing mock interview sessions
- Collecting user feedback and ratings
- Tracking interview performance metrics
- Analyzing preparation effectiveness
- Generating insights for improvement

### Key Capabilities
- Real-time rating updates
- Comprehensive review management
- User profile tracking
- Performance analytics
- Feedback aggregation

## 🏗️ Architecture

### System Flow Diagram

![Screenshot (446)](https://github.com/user-attachments/assets/9fd0e6a3-789b-4cb9-a939-f40a62d2eac0)

### Database Schema

![db-schema-svg](https://github.com/user-attachments/assets/88af3d53-1b6d-476e-b48b-a9367db7bbf0)

# Mock Drive Rating System

A Spring Boot application that manages user ratings and reviews for mock drives, providing functionality to add, update, and aggregate ratings along with detailed reviews.

---

## 💡 Features

### User Management
- User registration and authentication
- Profile management
- Activity tracking
- Personalized dashboards

### Mock Drive Management
- Create and customize mock drives
- Question bank management
- Drive scheduling
- Performance tracking

### Rating System
- 5-star rating system
- Detailed review submissions
- Rating analytics
- Feedback aggregation

### Analytics
- Performance metrics
- Progress tracking
- Improvement suggestions
- Trend analysis

---

## 📚 API Documentation

### User APIs
| Method | Endpoint         | Description          | Request Body                                         | Response       |
|--------|------------------|----------------------|------------------------------------------------------|----------------|
| POST   | /api/users        | Create user          | `{"username": "string", "email": "string", "password": "string"}` | User object    |
| GET    | /api/users        | Get all users        | -                                                    | Array of users |
| GET    | /api/users/{id}   | Get user by ID       | -                                                    | User object    |
| PUT    | /api/users/{id}   | Update user          | User object                                          | Updated user   |
| DELETE | /api/users/{id}   | Delete user          | -                                                    | 204 No Content |

### Mock Drive APIs
| Method | Endpoint               | Description          | Request Body    | Response         |
|--------|------------------------|----------------------|-----------------|------------------|
| POST   | /api/mockdrives         | Create mock drive    | Mock drive object | Created mock drive |
| GET    | /api/mockdrives         | Get all mock drives  | -               | Array of mock drives |
| GET    | /api/mockdrives/{id}    | Get mock drive by ID | -               | Mock drive object |
| PUT    | /api/mockdrives/{id}    | Update mock drive    | Mock drive object | Updated mock drive |
| DELETE | /api/mockdrives/{id}    | Delete mock drive    | -               | 204 No Content |

### Review APIs
| Method | Endpoint                               | Description            | Request Body    | Response         |
|--------|----------------------------------------|------------------------|-----------------|------------------|
| POST   | /api/reviews                           | Create review          | Review object   | Created review   |
| GET    | /api/reviews/{id}                      | Get review by ID       | -               | Review object    |
| PUT    | /api/reviews/{id}                      | Update review          | Review object   | Updated review   |
| GET    | /api/reviews/user/{userId}             | Get user reviews       | -               | Array of reviews |
| GET    | /api/reviews/mockdrive/{driveId}       | Get mock drive reviews | -               | Array of reviews |

---

## 🛠️ Setup Instructions

### Prerequisites
- Java 11+
- Maven 3.6+
- PostgreSQL 13+
- Git

### Installation Steps

1. **Clone the repository**
    ```bash
    git clone https://github.com/yourusername/mock-drive-rating-system.git
    cd mock-drive-rating-system
    ```

2. **Configure database**
    ```bash
    # Create PostgreSQL database
    createdb mockdrive_ratings

    # Update application.properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/mockdrive_ratings
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3. **Build and run the application**
    ```bash
    # Build the project
    mvn clean install

    # Run the application
    mvn spring-boot:run
    ```

4. **Verify installation**
    ```bash
    curl http://localhost:8080/api/health
    ```

---

## 🧪 Testing Guide

### Unit Testing
```bash
# Run unit tests
mvn test

# Run specific test class
mvn test -Dtest=UserServiceTest



