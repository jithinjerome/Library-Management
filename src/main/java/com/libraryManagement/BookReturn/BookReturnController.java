package com.libraryManagement.BookReturn;



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
public class BookReturnController {

    @Autowired
    private BookService bookService;

    @PostMapping(path = "/return/{bookId}/{userId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId,@PathVariable Long userId)
    {
        String response = bookService.returnBook(userId,bookId);
        if(response.contains("Successfull")){
            return  ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
