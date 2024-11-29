package com.libraryManagement.Librarian;

import com.libraryManagement.Book.Book;
import com.libraryManagement.Book.BookRepository;
import com.libraryManagement.JWT.JwtUtil;
import com.libraryManagement.User.IssueReturnDTO;
import com.libraryManagement.User.Role;
import com.libraryManagement.User.User;
import com.libraryManagement.User.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibrarianService {
    @Autowired
    public IssuedBookRepository issuedBookRepository;

    @Autowired
    public UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReturnBookRepository returnBookRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private LibrarianRepository librarianRepository;

    // Register librarian
    public Object registerUser(Librarian librarian) {
        if (librarian.getRole() == Role.ADMIN) {
            throw new IllegalArgumentException("Registration as ADMIN is not allowed.");
        }

        // Assign LIBRARIAN role automatically
        if (librarian.getRole() == null) {
            librarian.setRole(Role.LIBRARIAN);
        }

        librarian.setPassword(passwordEncoder.encode(librarian.getPassword()));
        return librarianRepository.save(librarian);
    }

    // Login librarian
    public ResponseEntity<?> loginUser(String email, String password) {
        Optional<Librarian> librarianOptional = librarianRepository.findByEmail(email);

        if (librarianOptional.isPresent()) {
            Librarian librarian = librarianOptional.get();
            if (passwordEncoder.matches(password, librarian.getPassword())) {
                String token = jwtUtil.generateToken(librarian.getEmail(), librarian.getRole().name());
                return ResponseEntity.ok().body(token);
            } else {
                return ResponseEntity.badRequest().body("Invalid credentials: Password mismatch");
            }
        }
        return ResponseEntity.badRequest().body("Invalid credentials: Librarian not found");
    }


    @Transactional
    public ResponseEntity<List<IssueReturnDTO>> getIssuedBooks(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        List<IssueReturnDTO> issueReturnDTOS = new ArrayList<>();

        if (userOptional.isPresent()) {
            List<IssuedBooks> IssuedBookList = issuedBookRepository.findByUserId(userId);
            if (!IssuedBookList.isEmpty()) {
                for (IssuedBooks issuedBooks : IssuedBookList) {
                    User user = userOptional.get();
                    IssueReturnDTO issueReturnDTO = new IssueReturnDTO();
                    issueReturnDTO.setName(user.getName());
                    issueReturnDTO.setIssueDate(issuedBooks.getIssueDate());
                    Optional<Book> bookOptional = bookRepository.findById(issuedBooks.getBook_Id());
                    if (bookOptional.isPresent()) {
                        Book book = bookOptional.get();
                        issueReturnDTO.setTitle(book.getTitle());
                        Optional<ReturnBooks> returnBooksOptional = returnBookRepository.findByUserIdAndBookId(userId, book.getId());
                        if (returnBooksOptional.isPresent()) {
                            ReturnBooks returnBooks = returnBooksOptional.get();
                            issueReturnDTO.setReturnDate(returnBooks.getReturnDate());
                        } else {
                            issueReturnDTO.setReturnDate(null);
                        }
                    }
                    issueReturnDTOS.add(issueReturnDTO);

                }
                return new ResponseEntity<>(issueReturnDTOS, HttpStatus.OK);
            }
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);

        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<BookReturnDTO>> returnedBooks(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        List<BookReturnDTO> bookreturns = new ArrayList<>();
        if (userOptional.isPresent()) {
            List<ReturnBooks> returnBooksList = returnBookRepository.findByUserId(userId);
            if (!returnBooksList.isEmpty()) {
                for (ReturnBooks returnBooks : returnBooksList) {
                    BookReturnDTO bookReturnDTO = new BookReturnDTO();
                    Optional<Book> bookOptional = bookRepository.findById(returnBooks.getBookId());
                    if (bookOptional.isPresent()) {
                        User user = userOptional.get();
                        Book book = bookOptional.get();
                        bookReturnDTO.setName(user.getName());
                        bookReturnDTO.setTitle(book.getTitle());
                        bookReturnDTO.setReturnDate(returnBooks.getReturnDate());
                    }
                    bookreturns.add(bookReturnDTO);
                }
                return new ResponseEntity<>(bookreturns, HttpStatus.OK);
            }
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
