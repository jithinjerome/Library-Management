package com.libraryManagement.Book;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
@Table(name = "books")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NotNull
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "categoryId")
    private Long categoryId;
    @Column(name = "category")
    private String category;
    @Column(name = "languageId")
    private Long languageId;
    @Column(name = "language")
    private String language;

}
