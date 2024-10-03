package com.libraryManagement.BookIssue;

import com.libraryManagement.Book.Book;
import com.libraryManagement.Book.BookRepository;
import com.libraryManagement.BookReturn.ReturnBookRepository;
import com.libraryManagement.BookReturn.ReturnBooks;
import com.libraryManagement.User.IssueReturnDTO;
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
public class IssuedBookService {

    @Autowired
    public IssuedBookRepository issuedBookRepository;

    @Autowired
    public UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReturnBookRepository returnBookRepository;

    public ResponseEntity<List<IssueReturnDTO>> getIssuedBooks(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        List<IssueReturnDTO> issueReturnDTOS = new ArrayList<>();

        if(userOptional.isPresent())
        {
            List<IssuedBooks> IssuedBookList = issuedBookRepository.findByuserId(userId);
            if(!IssuedBookList.isEmpty())
            {
                for (IssuedBooks issuedBooks:IssuedBookList)
                {
                    User user = userOptional.get();
                    IssueReturnDTO issueReturnDTO = new IssueReturnDTO();
                    issueReturnDTO.setName(user.getName());
                    issueReturnDTO.setIssueDate(issuedBooks.getIssueDate());
                    Optional<Book> bookOptional = bookRepository.findById(issuedBooks.getBook_Id());
                    if(bookOptional.isPresent())
                    {
                        Book book = bookOptional.get();
                        issueReturnDTO.setTitle(book.getTitle());
                        Optional<ReturnBooks> returnBooksOptional = returnBookRepository.findByUserIdAndBookId(userId,book.getId());
                        if(returnBooksOptional.isPresent())
                        {
                            ReturnBooks returnBooks = returnBooksOptional.get();
                            issueReturnDTO.setReturnDate(returnBooks.getReturnDate());
                        }else {
                            issueReturnDTO.setReturnDate(null);
                        }
                    }
                    issueReturnDTOS.add(issueReturnDTO);

                }
                return new ResponseEntity<>(issueReturnDTOS,HttpStatus.OK);
            }
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);

        }
        else
        {
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        }
    }

}
