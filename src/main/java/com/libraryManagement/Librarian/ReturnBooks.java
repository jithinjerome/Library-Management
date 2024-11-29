package com.libraryManagement.Librarian;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "ReturnBooks")
public class ReturnBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "bookId")
    private Long bookId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "returnDate")
    private LocalDate returnDate;
}
