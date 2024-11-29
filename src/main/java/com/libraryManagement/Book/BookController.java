package com.libraryManagement.Book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    private final String adminEmail = "admin@example.com";

    //Display list of all books.
    @GetMapping(path = "/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    //Display list of all books having same category.
    @GetMapping(path = "/books/category/{id}")
    public List<Book> bookByCategory(@PathVariable("id") Long categoryId){
        return bookService.getBookByCategory(categoryId);
    }

    //Display list of all books with same author.
    @GetMapping(path = "/books/{author}")
    public ResponseEntity<List<Book>> bookByAuthor(@PathVariable String author){
        return bookService.getBookByAuthor(author);
        //return new ResponseEntity<>(bookService.getBookByAuthor(author),HttpStatus.OK);

//        List<Book> authorBooks = bookService.getBookByAuthor(author);
//        if(authorBooks.isEmpty())
//        {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        else {
//            return new ResponseEntity<>(authorBooks,HttpStatus.OK);
//        }
    }

    //Display list of all books that comes under same language.
    @GetMapping(path = "/books/language/{id}")
    public List<Book> bookByLanguage(@PathVariable("id") Long languageId){
        return bookService.getBookByLanguage(languageId);
    }

    //Display all books that comes under given language and category.
    @GetMapping(path = "/books/{catId}/{lanId}")
    public List<Book> bookByCategoryAndLanguage(@PathVariable("catId") Long categoryId, @PathVariable("lanId") Long languageId){
        return bookService.getBookByCategoryAndLanguage(categoryId,languageId);
    }


    //Adding a book to database.
    @PostMapping(path = "/addBook")
//    public ResponseEntity<?> newBook(@RequestHeader("email")String email, @RequestBody Book book){
//        if(!email.equals(adminEmail))
//        {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
//        }
//        return ResponseEntity.ok(bookService.newBook(book));
//    }

    public ResponseEntity<?> newBook(@RequestParam Long categoryId,@RequestParam Long languageId, @RequestBody Book book){
        return bookService.addBook(categoryId,languageId,book);
    }

    //Updating a book.
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    //Category update of a book.
    @PutMapping(path = "/category/{id}")
    public ResponseEntity<?> categoryUpdate(@PathVariable Long id,@RequestParam Long categoryId,@RequestParam Long newCategoryId){
//        Book updateBook = bookService.categoryUpdate(id,categoryId);
//        return ResponseEntity.ok(updateBook);
        return bookService.categoryUpdate(id,categoryId,newCategoryId);
    }



    //Language update of a book.
    @PutMapping(path = "/language/{id}")
    public ResponseEntity<?> languageUpdate(@PathVariable Long id, @RequestParam Long languageId, @RequestParam Long newLanguageId)
    {
//        Book updateBook = bookService.languageUpdate(id,languageId);
//        return  ResponseEntity.ok(updateBook);
        return bookService.languageUpdate(id,languageId,newLanguageId);
    }

    //Delete a book by its id.
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book Deleted Successfully");
    }
}
