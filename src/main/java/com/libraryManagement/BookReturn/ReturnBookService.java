package com.libraryManagement.BookReturn;

import com.libraryManagement.Book.Book;
import com.libraryManagement.Book.BookRepository;
import com.libraryManagement.User.User;
import com.libraryManagement.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReturnBookService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReturnBookRepository returnBookRepository;


    public ResponseEntity<List<BookReturnDTO>> returnedBooks(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        List<BookReturnDTO> bookreturns = new ArrayList<>();
        if(userOptional.isPresent())
        {
            List<ReturnBooks> returnBooksList = returnBookRepository.findByUserId(userId);
            if(!returnBooksList.isEmpty()){
                for(ReturnBooks returnBooks: returnBooksList)
                {
                    BookReturnDTO bookReturnDTO = new BookReturnDTO();
                    Optional<Book> bookOptional = bookRepository.findById(returnBooks.getBookId());
                    if(bookOptional.isPresent()){
                        User user = userOptional.get();
                        Book book = bookOptional.get();
                        bookReturnDTO.setName(user.getName());
                        bookReturnDTO.setTitle(book.getTitle());
                        bookReturnDTO.setReturnDate(returnBooks.getReturnDate());
                    }
                    bookreturns.add(bookReturnDTO);
                }
                return new ResponseEntity<>(bookreturns,HttpStatus.OK);
            }
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);

        }else {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
