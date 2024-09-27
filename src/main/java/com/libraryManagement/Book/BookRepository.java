package com.libraryManagement.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {


    Optional<Book> findByIdAndCategoryId(Long id, Long categoryId);
    Optional<Book> findByIdAndLanguageId(Long id, Long languageId);
}
