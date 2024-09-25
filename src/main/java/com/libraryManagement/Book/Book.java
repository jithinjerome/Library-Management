package com.libraryManagement.Book;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "books")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "categoryId")
    private Long categoryId;
    @Column(name = "category")
    private String category;
    @Column(name = "languageId")
    private Long languageId;
    @Column(name = "language")
    private Long language;

}
