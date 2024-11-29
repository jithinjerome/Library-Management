# Library Management System

This project is a **Library Management** System developed using **Java** and **Spring Boot**. It facilitates the efficient management of book inventories and user interactions with the library. The system includes modules for **Admin**, **Librarian**, and **User**, each with specific roles and functionalities.

## Features

### Admin Module:
- Admin login
- Add new books to the library.
- Remove books from the library.
- Update existing book details.
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
- View all categories.
- View all languages.
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
  - View all categories.
  - View all languages.
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
- **POST** `/api/admin/login` - Admin login.
- **POST** `/api/book/addBook` - Add a new book.
- **POST** `/api/categoryDetails/addCategories` - Add a new category.
- **POST** `/api/language/addLanguage` - Add a new language.
- **DELETE** `/api/book/delete/{id}` - Remove a book.
- **PUT** `/api/book/update/{id}` - Update a book.
- **PUT** `/api/book/category/{id}` - Updating a book by its category.
- **PUT** `/api/book/language/{id}` - Updating a book by its language.
- **GET** `/api/book/books` - View all books.
- **GET** `/api/book/books/category/{id}` - View books by category.
- **GET** `/api/book/books/language/{id}` - View books by language.
- **GET** `/api/book/books/{catId}/{lanId}` - View books by category and language.
- **GET** `/api/categoryDetails/getCategory` - Get all categories.
- **GET** `/api/language/getLanguage` - Get all languages.
- **GET** `/api/book/books/{author}` - Book by the author name.

### Librarian Endpoints:
- **POST** `/api/librarian/register` - Librarian registration.
- **POST** `/api/librarian/login` - Librarian login.
- **POST** `/api/librarian/issue/{bookId}/{userId}` - Issuing book to users.
- **POST** `/api/librarian/{userId}/issuedBooksWithReturn` - Issued book details.
- **POST** `/api/librarian/return/{bookId}/{userId}` - Return of books from users and update the inventory.
- **POST** `/api/librarian/returnBooks/{userId}` - Returned book details.
- **GET** `/api/book/books` - View all books.
- **GET** `/api/book/books/category/{id}` - View books by category.
- **GET** `/api/book/books/language/{id}` - View books by language.
- **GET** `/api/book/books/{catId}/{lanId}` - View books by category and language.
- **GET** `/api/categoryDetails/getCategory` - Get all categories.
- **GET** `/api/language/getLanguage` - Get all languages.
- **GET** `/api/book/books/{author}` - Book by the author name.

### User Endpoints:
- **POST** `/api/user/register` - Register a new user.
- **POST** `/api/user/login` - Login to the system.
- **GET** `/api/book/books` - View all books.
- **GET** `/api/book/books/category/{id}` - View books by category.
- **GET** `/api/book/books/language/{id}` - View books by language.
- **GET** `/api/book/books/{catId}/{lanId}` - View books by category and language.
- **GET** `/api/categoryDetails/getCategory` - Get all categories.
- **GET** `/api/language/getLanguage` - Get all languages.
- **GET** `/api/book/books/{author}` - Book by the author name.


## For testing
https://drive.google.com/drive/u/0/folders/1Wga1G_yRUPjEplGldZ4ruGifiIKjfUYh



## Contact
For any inquiries or feedback, please contact:
- **Jithin Jerome**  
- jithinsjerome@gmail.com

