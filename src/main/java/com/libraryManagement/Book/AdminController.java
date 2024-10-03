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

    @GetMapping(path = "/books/category/{id}")
    public List<Book> bookByCategory(@PathVariable("id") Long categoryId){
        return bookService.getBookByCategory(categoryId);
    }

    @GetMapping(path = "/books/language/{id}")
    public List<Book> bookByLanguage(@PathVariable("id") Long languageId){
        return bookService.getBookByLanguage(languageId);
    }

    @GetMapping(path = "/books/{catId}/{lanId}")
    public List<Book> bookByCategoryAndLanguage(@PathVariable("catId") Long categoryId, @PathVariable("lanId") Long languageId){
        return bookService.getBookByCategoryAndLanguage(categoryId,languageId);
    }


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

    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateBook(@RequestHeader("email")String email, @PathVariable Long id, @RequestBody Book book)
    {
        if(!email.equals(adminEmail)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @PutMapping(path = "category/{id}")
    public ResponseEntity<?> categoryUpdate(@PathVariable Long id,@RequestParam Long categoryId,@RequestParam Long newCategoryId){
//        Book updateBook = bookService.categoryUpdate(id,categoryId);
//        return ResponseEntity.ok(updateBook);
        return bookService.categoryUpdate(id,categoryId,newCategoryId);
    }



    @PutMapping(path = "language/{id}")
    public ResponseEntity<?> languageUpdate(@PathVariable Long id, @RequestParam Long languageId, @RequestParam Long newLanguageId)
    {
//        Book updateBook = bookService.languageUpdate(id,languageId);
//        return  ResponseEntity.ok(updateBook);
        return bookService.languageUpdate(id,languageId,newLanguageId);
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
