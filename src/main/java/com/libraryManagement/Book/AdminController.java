package com.libraryManagement.Book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    private final String adminEmail = "admin@example.com";

    @GetMapping(path = "/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping(path = "/addBook")
//    public ResponseEntity<?> newBook(@RequestHeader("email")String email, @RequestBody Book book){
//        if(!email.equals(adminEmail))
//        {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
//        }
//        return ResponseEntity.ok(bookService.newBook(book));
//    }

    public ResponseEntity<?> newBook(@RequestParam Long categoryId, @RequestBody Book book){
        return bookService.addBook(categoryId,book);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateBook(@RequestHeader("email")String email, @PathVariable Long id, @RequestBody Book book)
    {
        if(!email.equals(adminEmail)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteById(@RequestHeader("email") String email, @PathVariable Long id)
    {
        if(!email.equals(adminEmail)){
            return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }

        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book Deleted Successfully");
    }
}
