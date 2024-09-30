package com.libraryManagement.BookIssue;

import com.libraryManagement.Book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/books")
public class IssuedBookController {

    @Autowired
    private BookService bookService;

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
}
