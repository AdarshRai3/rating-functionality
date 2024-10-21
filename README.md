Mock Drive Rating System
A Spring Boot application that manages user ratings and reviews for mock drives, providing functionality to add, update, and aggregate ratings along with detailed reviews.
📋 System Overview
The system allows users to:

Rate specific mock drives
Provide text reviews along with ratings
Update their existing ratings
View aggregate ratings for mock drives

🏗️ Architecture
System Design Diagram
mermaidCopyflowchart TD
    Client([Client/Postman])
    
    subgraph API[Spring Boot Application]
        Controllers[REST Controllers]
        Services[Service Layer]
        Repositories[Repository Layer]
        Entities[Entity Classes]
    end
    
    subgraph Database[PostgreSQL Database]
        Users[(Users Table)]
        Reviews[(Reviews Table)]
        MockDrives[(Mock Drives Table)]
    end
    
    Client <--> Controllers
    Controllers <--> Services
    Services <--> Repositories
    Repositories <--> Entities
    Entities <--> |Hibernate ORM| Database
    
    style API fill:#f0f7ff,stroke:#333
    style Database fill:#fff3e6,stroke:#333
Entity Relationship Diagram
mermaidCopyerDiagram
    USERS ||--o{ REVIEWS : creates
    MOCK_DRIVES ||--o{ REVIEWS : receives
    
    USERS {
        Long user_id PK
        String email
        String password
        String username
        DateTime created_at
    }
    
    REVIEWS {
        Long review_id PK
        Long user_id FK
        Long mock_drive_id FK
        int rating
        String review
        DateTime created_at
        DateTime updated_at
    }
    
    MOCK_DRIVES {
        Long mock_drive_id PK
        String title
        String questions
        double avg_rating
        DateTime created_at
    }
💻 Entity Classes
User Entity
javaCopy@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String username;
    
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;
    
    // Getters, Setters, and Constructors
}
MockDrive Entity
javaCopy@Entity
@Table(name = "mock_drives")
public class MockDrive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mockDriveId;
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String questions;
    
    @Column(name = "avg_rating")
    private Double avgRating;
    
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "mockDrive")
    private List<Review> reviews;
    
    // Getters, Setters, and Constructors
}
Review Entity
javaCopy@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "mock_drive_id")
    private MockDrive mockDrive;
    
    @Column(nullable = false)
    private Integer rating;
    
    @Column(columnDefinition = "TEXT")
    private String review;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Getters, Setters, and Constructors
}
🧪 API Testing Guide
User Management APIs
1. Create New User
httpCopyPOST http://localhost:8080/api/users

Request Body:
{
    "username": "dave_smith",
    "email": "kingdave.smith@example.com",
    "passwordHash": "mysocurepassword"
}

Response (200 OK):
{
    "userId": 1,
    "username": "dave_smith",
    "email": "kingdave.smith@example.com",
    "createdAt": "2024-04-21T10:30:00"
}
2. Get All Users
httpCopyGET http://localhost:8080/api/users

Response (200 OK):
[
    {
        "userId": 1,
        "username": "dave_smith",
        "email": "kingdave.smith@example.com",
        "createdAt": "2024-04-21T10:30:00"
    },
    // ... more users
]
3. Get User by ID
httpCopyGET http://localhost:8080/api/users/id/{id}

Response (200 OK):
{
    "userId": 1,
    "username": "dave_smith",
    "email": "kingdave.smith@example.com",
    "createdAt": "2024-04-21T10:30:00"
}
4. Update User by ID
httpCopyPUT http://localhost:8080/api/users/id/{id}

Request Body:
{
    "username": "dave_smith_updated",
    "email": "dave.smith.updated@example.com"
}

Response (200 OK):
{
    "userId": 1,
    "username": "dave_smith_updated",
    "email": "dave.smith.updated@example.com",
    "createdAt": "2024-04-21T10:30:00"
}
5. Delete User by ID
httpCopyDELETE http://localhost:8080/api/users/id/{id}

Response (204 No Content)
Mock Drive APIs
1. Create New Mock Drive
httpCopyPOST http://localhost:8080/api/mockdrives

Request Body:
{
    "title": "Spring Framework",
    "questions": [
        "What is the Spring Framework?",
        "What are the core modules of Spring?",
        "What is Dependency Injection?",
        "Explain the concept of AOP in Spring.",
        "What is Spring Boot?"
    ]
}

Response (200 OK):
{
    "mockDriveId": 1,
    "title": "Spring Framework",
    "questions": [...],
    "createdAt": "2024-04-21T11:00:00"
}
2. Get All Mock Drives
httpCopyGET http://localhost:8080/api/mockdrives

Response (200 OK):
[
    {
        "mockDriveId": 1,
        "title": "Spring Framework",
        "questions": [...],
        "avgRating": 4.5,
        "createdAt": "2024-04-21T11:00:00"
    },
    // ... more mock drives
]
3. Get Mock Drive by ID
httpCopyGET http://localhost:8080/api/mockdrives/id/{id}

Response (200 OK):
{
    "mockDriveId": 1,
    "title": "Spring Framework",
    "questions": [...],
    "avgRating": 4.5,
    "createdAt": "2024-04-21T11:00:00"
}
4. Update Mock Drive by ID
httpCopyPUT http://localhost:8080/api/mockdrives/id/{id}

Request Body:
{
    "title": "Updated Spring Framework",
    "questions": [...]
}

Response (200 OK):
{
    "mockDriveId": 1,
    "title": "Updated Spring Framework",
    "questions": [...],
    "avgRating": 4.5,
    "createdAt": "2024-04-21T11:00:00"
}
5. Delete Mock Drive by ID
httpCopyDELETE http://localhost:8080/api/mockdrives/id/{id}

Response (204 No Content)
Review APIs
1. Create Review
httpCopyPOST http://localhost:8080/api/reviews/users/id/{userId}/mockdrives/id/{mockDriveId}

Request Body:
{
    "rating": 5,
    "review": "Great mock drive experience!"
}

Response (200 OK):
{
    "reviewId": 1,
    "rating": 5,
    "review": "Great mock drive experience!",
    "userId": 1,
    "mockDriveId": 1,
    "createdAt": "2024-04-21T12:00:00"
}
2. Get Review by User and Mock Drive ID
httpCopyGET http://localhost:8080/api/reviews/users/id/{userId}/mockdrives/id/{mockDriveId}

Response (200 OK):
{
    "reviewId": 1,
    "rating": 5,
    "review": "Great mock drive experience!",
    "userId": 1,
    "mockDriveId": 1,
    "createdAt": "2024-04-21T12:00:00"
}
3. Update Review by User and Mock Drive ID
httpCopyPUT http://localhost:8080/api/reviews/users/id/{userId}/mockdrives/id/{mockDriveId}

Request Body:
{
    "rating": 4,
    "review": "Updated review - Good mock drive!"
}

Response (200 OK):
{
    "reviewId": 1,
    "rating": 4,
    "review": "Updated review - Good mock drive!",
    "userId": 1,
    "mockDriveId": 1,
    "updatedAt": "2024-04-21T13:00:00"
}
4. Update Review by ID
httpCopyPUT http://localhost:8080/api/reviews/id/{id}

Request Body:
{
    "rating": 4,
    "review": "Updated review content"
}

Response (200 OK):
{
    "reviewId": 1,
    "rating": 4,
    "review": "Updated review content",
    "updatedAt": "2024-04-21T13:00:00"
}
5. Get Review by ID
httpCopyGET http://localhost:8080/api/reviews/id/{id}

Response (200 OK):
{
    "reviewId": 1,
    "rating": 4,
    "review": "Updated review content",
    "userId": 1,
    "mockDriveId": 1,
    "createdAt": "2024-04-21T12:00:00",
    "updatedAt": "2024-04-21T13:00:00"
}
🧪 Testing with Postman

Import the following curl commands into Postman:

bashCopy# Create User
curl -X POST http://localhost:8080/api/users \
-H "Content-Type: application/json" \
-d '{
    "username": "dave_smith",
    "email": "kingdave.smith@example.com",
    "passwordHash": "mysocurepassword"
}'

# Create Mock Drive
curl -X POST http://localhost:8080/api/mockdrives \
-H "Content-Type: application/json" \
-d '{
    "title": "Spring Framework",
    "questions": [
        "What is the Spring Framework?",
        "What are the core modules of Spring?",
        "What is Dependency Injection?",
        "Explain the concept of AOP in Spring.",
        "What is Spring Boot?"
    ]
}'

# Create Review
curl -X POST http://localhost:8080/api/reviews/users/id/1/mockdrives/id/1 \
-H "Content-Type: application/json" \
-d '{
    "rating": 5,
    "review": "Great mock drive experience!"
}'

Set the environment to localhost:8080
Test each endpoint and verify the responses
For PUT and DELETE operations, make sure to use valid IDs from previous GET responses

📝 Response Status Codes

200 OK: Successful GET, PUT requests
201 Created: Successful POST requests
204 No Content: Successful DELETE requests
400 Bad Request: Invalid input data
404 Not Found: Resource not found
500 Internal Server Error: Server-side errors

🛠️ Setup and Installation

Prerequisites

Java 11 or higher
Maven
PostgreSQL


Application Properties

propertiesCopyspring.datasource.url=jdbc:postgresql://localhost:5432/mockdrive_ratings
spring.datasource.username=your_username
spring.datasource.password=your_password

# Hibernate properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Building and Running

bashCopy# Clone the repository
git clone [repository-url]

# Navigate to project directory
cd mock-drive-rating-system

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
📝 Response Status Codes

200 OK: Successful GET, PUT requests
201 Created: Successful POST requests
204 No Content: Successful DELETE requests
400 Bad Request: Invalid input data
404 Not Found: Resource not found
500 Internal Server Error: Server-side errors

🚀 Contributing

Fork the project
Create your feature branch (git checkout -b feature/AmazingFeature)
Commit your changes (git commit -m 'Add some AmazingFeature')
Push to the branch (git push origin feature/AmazingFeature)
Open a Pull Request

