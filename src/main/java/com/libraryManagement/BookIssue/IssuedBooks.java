package com.libraryManagement.BookIssue;


import com.libraryManagement.Book.Book;
import com.libraryManagement.User.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Issued_Books")
public class IssuedBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "issue_Date")
    private LocalDate issueDate;

}
