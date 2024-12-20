package com.libraryManagement.User;


import com.libraryManagement.Book.Book;
import com.libraryManagement.Book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class userController {

    @Autowired
    public BookService bookService;

    @Autowired
    public UserService userService;


    @GetMapping(path = "/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    //User registration.
    @PostMapping(path = "/register")
    public Object registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    //User login.
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password)
    {
        return userService.loginUser(email,password);
    }



}
