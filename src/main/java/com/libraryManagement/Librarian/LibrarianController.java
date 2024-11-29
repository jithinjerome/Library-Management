package com.libraryManagement.Librarian;

import com.libraryManagement.Book.BookService;
import com.libraryManagement.User.IssueReturnDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/librarian")
public class LibrarianController {

    @Autowired
    LibrarianService librarianService;

    @Autowired
    private BookService bookService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(@RequestBody Librarian librarian) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(librarianService.registerUser(librarian));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        return librarianService.loginUser(email, password);
    }

    @PostMapping(path = "/issue/{bookId}/{userId}")
    public ResponseEntity<String> issueBook(@PathVariable Long bookId,@PathVariable Long userId)
    {
        String response = bookService.issueBook(bookId,userId);
        if(response.contains("successfully"))
        {
            return ResponseEntity.ok(response);
        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping(path = "/{userId}/issuedBooksWithReturn")
    public ResponseEntity<List<IssueReturnDTO>> getIssuedBooks(@PathVariable Long userId){
        try {
            return librarianService.getIssuedBooks(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/return/{bookId}/{userId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId,@PathVariable Long userId)
    {
        String response = bookService.returnBook(userId,bookId);
        if(response.contains("Successfull")){
            return  ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping(path = "/returnBooks/{userId}")
    public ResponseEntity<List<BookReturnDTO>> returnDetail(@PathVariable Long userId){
        return librarianService.returnedBooks(userId);
    }
}
