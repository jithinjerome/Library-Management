# Library Management System

This project is a **Library Management** System developed using **Java** and **Spring Boot**. It facilitates the efficient management of book inventories and user interactions with the library. The system includes modules for **Admin**, **Librarian**, and **User**, each with specific roles and functionalities.

## Features

### Admin Module:
- Add new books to the library.
- Remove books from the library.
- Update existing book details.
- Delete books from the database.
- Add new categories.
- Update the existing category.
- View all categories.
- Add new languages.
- Update the existing language.
- View all languages.
- View all books.
- Filter books by category.
- Filter books by language.
- Filter books by both language and category.
- Filter book by the author name.

### Librarian Module
- Librarian Registration.
- Librarian Login.
- Issue books to users.
- **Return Books**: Handle the return of books from users and update the inventory.
- Track issued and returned books.
- View all books.
- Filter books by category.
- Filter books by language.
- Filter books by both language and category.
- Filter book by the author name.

### User Module:
- **Registration**: Users can sign up by providing personal details.
- **Login**: Users can log in to their accounts.
- **View Books**: Users can view all books available in the library.
- **Search and Filter Books**:
  - Filter books by category.
  - Filter books by language.
  - Filter books by both language and category.
  - Filter book by the author name.

### Book Issuing Feature:
- **Issue Books**: Registered users can issue books from the library.
- **Stock Management**: When a book is issued, its quantity is reduced in the database.
- **Validation**: The system checks if the user is registered and if the book is available in stock before issuing.

### Authentication & Authorization:
- Role-based access control for Admin, Librarian, and User.
- JWT-based token generation for secure authentication.
  
## Technologies Used
- **Java**: The core programming language used for development.
- **Spring Boot**: The framework used to build and run the backend.
- **PostgreSQL**: Database management system for persisting data.
- **Spring Security**: For secure authentication and authorization.
- **JWT (JSON Web Tokens)**: Used for session management and role-based access control.

## Project Structure
The project follows a typical Spring Boot architecture with Controllers, Services, Repositories, and Entities.
com.libraryManagement


### Key Entities:
- **Admin**: Represents the admin entity with attributes like name, email, password.
- **User**: Represents the user entity with attributes like name, email, password, and role.
- **Librarian**: Represents the librarian entity with attributes like name, email, password, and role.
- **Book**: Represents the book entity with attributes like title, author, quantity, category, and language.
- **IssuedBook**: Tracks the details of books issued by users, including the issue date and user details.
- **ReturnBook**: Tracks the details of books returned by users, including the return date and user details.
- **Category**: Stores all the related categories.
- **Language**: Stores all the supported languages.

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

