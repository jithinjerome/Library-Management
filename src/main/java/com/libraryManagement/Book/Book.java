package com.libraryManagement.Book;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "books")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private long id;
    private String title;
    private String author;
    private Long categoryId;
    private String category;

}
