package com.libraryManagement.User;


import com.libraryManagement.Book.Book;
import com.libraryManagement.Book.BookService;
import com.libraryManagement.BookIssue.IssuedBookRepository;
import com.libraryManagement.BookIssue.IssuedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class userController {

    @Autowired
    public BookService bookService;

    @Autowired
    public UserService userService;

    @Autowired
    public IssuedBookService issuedBookService;

    @GetMapping(path = "/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping(path = "/register")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password)
    {
        return userService.loginUser(email,password);
    }

    @GetMapping(path = "/{userId}/issuedBooksWithReturn")
    public ResponseEntity<List<IssueReturnDTO>> getIssuedBooks(@PathVariable Long userId){
        try {
            return issuedBookService.getIssuedBooks(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
