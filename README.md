# Library Management System

This project is a **Library Management System** developed using **Java** and **Spring Boot**. It includes two main modules: **Admin** and **User**, with functionalities to manage books, view books, and issue books. The system efficiently manages book inventory and facilitates user interaction with the library.

## Features

### Admin Module:
- Add new books to the library.
- Remove books from the library.
- Update existing book details.
- Delete books from the database.
- View all books.
- Filter books by category.
- Filter books by language.
- Filter books by both language and category.

### User Module:
- **Registration**: Users can sign up by providing personal details.
- **Login**: Users can log in to their accounts.
- **View Books**: Users can view all books available in the library.
- **Search and Filter Books**:
  - Filter books by category.
  - Filter books by language.
  - Filter books by both language and category.

### Book Issuing Feature:
- **Issue Books**: Registered users can issue books from the library.
- **Stock Management**: When a book is issued, its quantity is reduced in the database.
- **Validation**: The system checks if the user is registered and if the book is available in stock before issuing.
  
## Technologies Used
- **Java**: The core programming language used for development.
- **Spring Boot**: The framework used to build and run the backend.

## Project Structure
The project follows a typical Spring Boot architecture with Controllers, Services, Repositories, and Entities.

### Key Entities:
- **Book**: Represents the book entity with attributes like title, author, quantity, category, and language.
- **User**: Represents the user entity with attributes like name, email, password, and role (Admin or User).
- **IssuedBook**: Tracks the details of books issued by users, including the issue date and user details.

## API Endpoints

### Admin Endpoints:
- **POST** `/addBook` - Add a new book.
- **DELETE** `{id}` - Remove a book.
- **PUT** `{id}` - Update a book.
- **PUT** `category/{id}` - Updating a book by its category.
- **PUT** `language/{id}` - Updating a book by its language.
- **GET** `/books` - View all books.
- **GET** `/books/category/{id}` - View books by category.
- **GET** `/books/language/{id}` - View books by language.
- **GET** `/books/{catId}/{lanId}` - View books by category and language.

### User Endpoints:
- **POST** `/register` - Register a new user.
- **POST** `/login` - Login to the system.
- **GET** `/books` - View all books.
- **GET** `/books/category/{id}` - View books by category.
- **GET** `/books/language/{id}` - View books by language.
- **GET** `/books/{catId}/{lanId}` - View books by category and language.
- **POST** `/books/issue/{bookId}/{userId}` - Issue a book (only for registered users).

## Future Enhancements
- Add return functionality for issued books.
- Implement a notification system for book availability.
- Add pagination and sorting to book views.







## Contact
For any inquiries or feedback, please contact:
- **Jithin Jerome**  
- jithinsjerome@gmail.com

