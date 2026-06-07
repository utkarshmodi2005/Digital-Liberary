# Digital Library Management System

## Overview

Digital Library Management System is a backend application developed using Spring Boot and PostgreSQL. It allows users to manage books, issue and return books, track active borrowings, and enforce subscription-based borrowing limits.

The project follows a layered architecture using Controllers, Services, Repositories, DTOs, and Mappers.

---

## Features

### User Management

* Create User
* Update User
* Delete User
* Get User By ID
* Get All Users

### Book Management

* Add Book
* Update Book
* Delete Book
* Get Book By ID
* Get All Books

### Book Issue & Return

* Issue a book to a user
* Return an issued book
* Prevent duplicate issue records
* Track issue date and expiry date

### Subscription Based Access

* NOTSUBSCRIBED
* PLUS (7 Days Access)
* PRO (14 Days Access)
* PREMIUM (1 Month Access)

Expiry date is automatically calculated based on the user's subscription plan.

### Search Functionality

* Search books by title
* Search books by author

### Analytics

* Count books issued by a user
* View active books currently issued to a user
* View most borrowed books

---

## Technologies Used

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Lombok
* REST APIs

---

## Project Structure

src/main/java

controller/

service/

repository/

entity/

dto/

mapper/

enums/

---

## Database Design

### User Entity

* id
* name
* email
* subscriptionType

### Book Entity

* id
* title
* author
* category

### Book Issue Entity

* id
* userEntity
* bookEntity
* issueDate
* expiryDate
* returned

Relationships:

* One User → Many Book Issues
* One Book → Many Book Issues

---

## API Endpoints

### User APIs

POST /users

GET /users/{id}

GET /users

PUT /users/{id}

DELETE /users/{id}

### Book APIs

POST /books

GET /books/{id}

GET /books

PUT /books/{id}

DELETE /books/{id}

GET /books/search/title

GET /books/search/author

### Book Issue APIs

POST /book-issue

GET /book-issue/{id}

DELETE /book-issue/{id}

GET /book-issue/user/{userId}

GET /book-issue/active/{userId}

GET /book-issue/count/{userId}

GET /book-issue/top-borrowed

---

## Future Improvements

* JWT Authentication
* Role Based Authorization
* Frontend UI (React)
* Swagger Documentation
* Email Notifications
* Book Recommendations

---

## Author

Utkarsh Modi

B.Tech Student

Backend Developer (Java + Spring Boot)
